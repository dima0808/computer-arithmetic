package com.civka.calculatordemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/maintenance/**").hasRole("ADMIN")
                        .requestMatchers("/feedback/answer/**").hasRole("ADMIN")
                        .requestMatchers("/feedback/**").authenticated()
                        .requestMatchers("/afdks/**").authenticated()
                        .requestMatchers("/css/**", "/img/**", "/font/**", "/scripts/**").permitAll()
                        .anyRequest().permitAll()
        ).formLogin(login ->
                login
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateUser")
                        .defaultSuccessUrl("/")
                        .permitAll()
        ).logout(logout ->
                logout.logoutSuccessUrl("/")
                        .permitAll()
        ).rememberMe(rememberMe -> rememberMe
                .key("uniqueAndSecret")
                .tokenValiditySeconds(16000000));

        return http.build();
    }
}
