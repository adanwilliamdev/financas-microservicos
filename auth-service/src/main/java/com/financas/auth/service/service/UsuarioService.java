package com.financas.auth.service.service;

import com.financas.auth.service.model.Usuario;
import com.financas.auth.service.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    
    @Transactional
    public Usuario criarUsuario(String email, String senha, String nome) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("Email já cadastrado");
        }
        
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha); // Simplificado - em produção usar BCrypt
        usuario.setNome(nome);
        usuario.setAtivo(true);
        usuario.setDataCriacao(LocalDateTime.now());
        
        return usuarioRepository.save(usuario);
    }
}