package com.financas.auth.service.config;

import com.financas.auth.service.model.Usuario;
import com.financas.auth.service.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializerRunner implements CommandLineRunner {
    
    private final UsuarioRepository usuarioRepository;
    
    public DataInitializerRunner(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    @Override
    public void run(String... args) {
        // Criar usuário admin
        if (!usuarioRepository.existsByEmail("admin@financas.com")) {
            Usuario admin = new Usuario();
            admin.setEmail("admin@financas.com");
            admin.setSenha("admin123");
            admin.setNome("Administrador");
            admin.setAtivo(true);
            usuarioRepository.save(admin);
            System.out.println("✅ Usuário admin criado: admin@financas.com / admin123");
        }
        
        // Criar usuário teste
        if (!usuarioRepository.existsByEmail("usuario@financas.com")) {
            Usuario usuario = new Usuario();
            usuario.setEmail("usuario@financas.com");
            usuario.setSenha("usuario123");
            usuario.setNome("Usuário Teste");
            usuario.setAtivo(true);
            usuarioRepository.save(usuario);
            System.out.println("✅ Usuário teste criado: usuario@financas.com / usuario123");
        }
    }
}