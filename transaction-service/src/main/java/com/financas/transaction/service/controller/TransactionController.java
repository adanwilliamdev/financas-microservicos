package com.financas.transaction.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@RestController
@RequestMapping("/api/transacoes")
public class TransactionController {
    
    private List<Map<String, Object>> transacoes = new ArrayList<>();
    
    public TransactionController() {
        // Dados mock
        Map<String, Object> t1 = new HashMap<>();
        t1.put("id", 1);
        t1.put("descricao", "Salário Janeiro");
        t1.put("valor", 50000.00);
        t1.put("data", "2026-07-01");
        t1.put("categoria", "Salário");
        t1.put("tipo", "RECEITA");
        transacoes.add(t1);
        
        Map<String, Object> t2 = new HashMap<>();
        t2.put("id", 2);
        t2.put("descricao", "Mercado");
        t2.put("valor", 350.50);
        t2.put("data", "2026-07-02");
        t2.put("categoria", "Alimentação");
        t2.put("tipo", "DESPESA");
        transacoes.add(t2);
        
        Map<String, Object> t3 = new HashMap<>();
        t3.put("id", 3);
        t3.put("descricao", "Transporte");
        t3.put("valor", 150.00);
        t3.put("data", "2026-07-03");
        t3.put("categoria", "Transporte");
        t3.put("tipo", "DESPESA");
        transacoes.add(t3);
    }
    
    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "transaction-service");
        response.put("message", "Transaction Service is running!");
        return response;
    }
    
    @GetMapping
    public List<Map<String, Object>> listar() {
        return transacoes;
    }
    
    @PostMapping
    public Map<String, Object> criar(@RequestBody Map<String, Object> transacao) {
        int newId = transacoes.size() + 1;
        transacao.put("id", newId);
        transacoes.add(transacao);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Transação criada com sucesso!");
        response.put("data", transacao);
        return response;
    }
    
    @GetMapping("/saldo")
    public Map<String, Object> saldo() {
        double receitas = 0;
        double despesas = 0;
        
        for (Map<String, Object> t : transacoes) {
            Object valorObj = t.get("valor");
            double valor = 0;
            if (valorObj instanceof Number) {
                valor = ((Number) valorObj).doubleValue();
            }
            String tipo = (String) t.get("tipo");
            if ("RECEITA".equals(tipo)) {
                receitas += valor;
            } else {
                despesas += valor;
            }
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("saldoTotal", receitas - despesas);
        response.put("receitas", receitas);
        response.put("despesas", despesas);
        response.put("quantidade", transacoes.size());
        return response;
    }
    
    @GetMapping("/exportar")
    public ResponseEntity<byte[]> exportar() {
        try {
            StringBuilder csv = new StringBuilder();
            csv.append("id,descricao,valor,data,categoria,tipo,observacao\n");
            
            for (Map<String, Object> t : transacoes) {
                csv.append(t.get("id")).append(",");
                csv.append(t.get("descricao")).append(",");
                csv.append(t.get("valor")).append(",");
                csv.append(t.get("data")).append(",");
                csv.append(t.get("categoria")).append(",");
                csv.append(t.get("tipo")).append(",");
                csv.append(t.get("observacao") != null ? t.get("observacao") : "").append("\n");
            }
            
            byte[] bytes = csv.toString().getBytes();
            
            return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=transacoes.csv")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(bytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/importar")
    public ResponseEntity<?> importar(@RequestParam("arquivo") MultipartFile arquivo) {
        try {
            List<Map<String, Object>> novasTransacoes = new ArrayList<>();
            
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))) {
                String linha;
                boolean primeiraLinha = true;
                
                while ((linha = reader.readLine()) != null) {
                    if (primeiraLinha) {
                        primeiraLinha = false;
                        continue;
                    }
                    
                    String[] dados = linha.split(",");
                    if (dados.length >= 6) {
                        Map<String, Object> transacao = new HashMap<>();
                        transacao.put("id", transacoes.size() + 1);
                        transacao.put("descricao", dados[0].trim());
                        transacao.put("valor", Double.parseDouble(dados[1].trim()));
                        transacao.put("data", dados[2].trim());
                        transacao.put("categoria", dados[3].trim());
                        transacao.put("tipo", dados[4].trim());
                        transacao.put("observacao", dados.length > 5 ? dados[5].trim() : "");
                        transacoes.add(transacao);
                        novasTransacoes.add(transacao);
                    }
                }
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Importadas " + novasTransacoes.size() + " transações com sucesso!");
            response.put("quantidade", novasTransacoes.size());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}