package com.example.springsecuritybasic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.PasswordManagementDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService users() {
        UserDetails normalUser = User
                .withUsername("ravi")
                .password(passwordEncoder().encode("password"))
                .roles("NORMAL").build();
        UserDetails adminUser = User
                .withUsername("ankit")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN").build();
        UserDetails userAbc = User
                .withUsername("abc")
                .password(passwordEncoder().encode("password"))
                .roles("abc").build();


        return new InMemoryUserDetailsManager(normalUser, adminUser,userAbc);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
     httpSecurity.csrf().disable().authorizeHttpRequests(authorize -> {
         try {
             authorize.
                     requestMatchers("/api/admin")
                     .hasRole("abc")
                     .requestMatchers("/api/user")
                     .hasRole("NORMAL")
                     .requestMatchers("/api/public")
                     .permitAll()
                     .anyRequest()
                     .authenticated()
                     .and()
                     .formLogin();
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }).httpBasic();
     return httpSecurity.build();
    }

}
