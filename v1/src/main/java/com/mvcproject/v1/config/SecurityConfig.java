package com.mvcproject.v1.config;

import com.mvcproject.v1.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserServiceImpl userService;

    public SecurityConfig(UserServiceImpl userService) {
        this.userService = userService;
    }

    
    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }

        @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(userDetailsService())
                   .passwordEncoder(NoOpPasswordEncoder.getInstance())  
                   .and()
                   .build();
    }

    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); 
        return provider;
    }
  

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        
        
        return httpSecurity
         
        .csrf().disable() 
                     .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/login","/doc","/swagger-ui/index.html","/swagger-ui/**", "/v3/api-docs/**","/swagger-ui.html","/swagger-ui.html/**","/", "/css/**", "/js/**", "/images/**").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin()
                .successHandler(successHandler())
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/login")
                .maximumSessions(1)
                .expiredUrl("/login")
                .and()
                .sessionFixation()
                .migrateSession()
                .and()
                .build();
    }

    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> {
            response.sendRedirect("/parqueadero/");
        });
    }
}
