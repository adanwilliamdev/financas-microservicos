package com.financas.report.service.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/relatorios")
public class ReportController {
    
    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "report-service");
        response.put("message", "Report Service is running!");
        return response;
    }
    
    @GetMapping("/resumo")
    public Map<String, Object> resumo() {
        Map<String, Object> response = new HashMap<>();
        response.put("totalReceitas", 50000.00);
        response.put("totalDespesas", 1200.00);
        response.put("saldo", 48800.00);
        response.put("quantidadeTransacoes", 3);
        
        Map<String, Double> categorias = new HashMap<>();
        categorias.put("Alimentação", 350.50);
        categorias.put("Salário", 50000.00);
        categorias.put("Transporte", 849.50);
        response.put("categorias", categorias);
        
        response.put("mensagem", "Relatório gerado com sucesso!");
        return response;
    }
}