package com.claim.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.claim.gateway.security.JwtAuthenticationFilter;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter filter;

    public SecurityConfig(JwtAuthenticationFilter filter) {
        this.filter = filter;
    }

    // ✅ Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Security Configuration
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(cors -> {})

                .authorizeExchange(exchange -> exchange

                        // 🔓 Public
                        .pathMatchers("/auth/**").permitAll()

                        // =========================
                        // 🔐 POLICY RULES
                        // =========================

                        .pathMatchers(HttpMethod.GET, "/api/policies/**")
                            .hasAnyRole("USER", "ADMIN")

                        .pathMatchers(HttpMethod.PUT, "/api/policies/*/assign/**")
                            .hasRole("USER")

                        .pathMatchers("/api/policies/**")
                            .hasRole("ADMIN")

                        // =========================
                        // 🔐 HOSPITAL (ADMIN)
                        // =========================
                      

                        // =========================
                        // 🔐 RECEIPT (USER + ADMIN)
                        // =========================
                        .pathMatchers("/api/receipt/**")
                            .hasAnyRole("USER", "ADMIN")

                        // =========================
                        // 🔐 CLAIMS (USER + ADMIN)
                        // =========================
                        .pathMatchers("/api/claims/**")
                            .hasAnyRole("USER", "ADMIN")

                        // =========================
                        // 🔐 USER ONLY
                        // =========================
                        .pathMatchers("/api/patients/**")
                            .hasRole("USER")

                        .anyExchange().authenticated()
                )

                .addFilterAt(filter, SecurityWebFiltersOrder.AUTHENTICATION)

                .build();
    }


    // ✅ CORS Configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
