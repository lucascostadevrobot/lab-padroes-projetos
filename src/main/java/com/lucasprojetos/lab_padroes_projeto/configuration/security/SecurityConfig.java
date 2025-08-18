package com.lucasprojetos.lab_padroes_projeto.configuration.security;

import com.lucasprojetos.lab_padroes_projeto.services.SecurityDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private SecurityDatabaseService securityDatabaseService;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityDatabaseService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpRequests) throws Exception {
        httpRequests
                .csrf(csrf -> csrf.disable()) // desativa CSRF para APIs REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/publico/**").permitAll() // endpoints públicos)
                        .requestMatchers("/salvarClientes").hasAnyRole("ATENDIMENTO", "MANAGERS", "GESTORES")
                        .anyRequest().authenticated() // o restante exige login
                )
                .httpBasic(Customizer.withDefaults());  // usa autenticação básica

        return httpRequests.build();
    }

   /* @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userAtendimento = User.withUsername("atendimento")
                .password(passwordEncoder().encode("user123"))
                .roles("ATENDIMENTO")
                .build();


        UserDetails userGestor = User.withUsername("gestor")
                .password(passwordEncoder().encode("user123"))
                .roles("GESTORES")
                .build();

        UserDetails UserAdmin = User.withUsername("admin")
                .password(passwordEncoder().encode("master123"))
                .roles("MANAGERS")
                .build();

        return new InMemoryUserDetailsManager(userAtendimento, userGestor, UserAdmin);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // ou NoOpPasswordEncoder para testes
    }
}
