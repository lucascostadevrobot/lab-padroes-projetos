package com.lucasprojetos.lab_padroes_projeto.configuration.security;

import com.lucasprojetos.lab_padroes_projeto.model.usuarios.Usuario;
import com.lucasprojetos.lab_padroes_projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class SecurityDatabaseService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Procura o usuário no banco
        Usuario usuario = usuarioRepository.findByUserName(username);

        // Se o usuário não for encontrado, lança exceção
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Converte os roles do usuário para Authorities
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(
                usuario.getRoles().stream()
                        .map(role -> "ROLE_" + role)
                        .toArray(String[]::new)
        );

        // Cria o UserDetails com as informações do usuário e suas authorities
        return new org.springframework.security.core.userdetails.User(
                usuario.getUserName(),
                usuario.getPassword(),
                authorities
        );
    }
}