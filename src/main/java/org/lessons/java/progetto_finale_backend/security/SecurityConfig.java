package org.lessons.java.progetto_finale_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final DatabaseUserDetailService databaseUserDetailService;

    public SecurityConfig(DatabaseUserDetailService databaseUserDetailService) {
        this.databaseUserDetailService = databaseUserDetailService;
    }

    // @Bean
    // @SuppressWarnings("removal")
    // SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // http.authorizeHttpRequests()
    // .requestMatchers("/videogames/create",
    // "/videogames/edit/**").hasAuthority("ADMIN")
    // .requestMatchers(HttpMethod.POST, "/videogames/**").hasAuthority("ADMIN")
    // .requestMatchers("/genres", "/genres/**").hasAuthority("ADMIN")
    // .requestMatchers("/videogames", "/videogames/**").hasAnyAuthority("USER",
    // "ADMIN")
    // .anyRequest().permitAll()
    // .and().formLogin()
    // .and().logout()
    // .and().exceptionHandling();

    // return http.build();
    // }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/videogames/create", "/videogames/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/videogames/**").hasAuthority("ADMIN")
                .requestMatchers("/genres", "/genres/**").hasAuthority("ADMIN")
                .requestMatchers("/videogames", "/videogames/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/").permitAll()
                .requestMatchers("/**").permitAll())
                .formLogin(Customizer.withDefaults())
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(databaseUserDetailService); // usa quello iniettato
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
