package com.example.CadastroProdutos.infra.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


//ANOTATION PARA O SPRING HABILITAR WEB SECURITY E A CLASSE VAI TER A CONFIGURACAO DA MESMA


/*
CLASSE DE CONFIGURACAO DO MEU SPRTNG SECURITY

FILTERCHAIN CONFIGURO TUDO SOBRE MEU HTTP COMO AUTORIZACAO DE USUARIOS, SESSOES... COM HTTPSECURITY

AUTHENTICATORMANEGER É APRA AUTENTICAR O USUARIO
 */
@Configuration
public class SecurityConfiguration {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //PARA NAO GUARDAR INFORMACOES DA SECAO
                .formLogin(form -> form.loginPage("/login"))
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> { //AQUI ESOTU AUTORIZANDO AS ROLES A FAZEREM AS REQUISCOES
                        authorize.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                        authorize.requestMatchers(HttpMethod.POST, "/auth/register").hasRole("ADMIN");
                        authorize.requestMatchers(HttpMethod.POST, "/products/add").hasAnyRole("USER", "ADMIN");
                        authorize.requestMatchers(HttpMethod.POST, "/products/{id}").hasAnyRole("USER", "ADMIN");
                        authorize.requestMatchers(HttpMethod.GET, "/products/").hasRole("ADMIN");
                        authorize.anyRequest().authenticated();
                })
                    .build();

    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    //CRIPTOGRAFIA DA SENHA
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(10);
    }
}
