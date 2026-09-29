package com.lsxuan.saas.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Spring Security 核心过滤器链
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // MVP 阶段关闭 CSRF，方便使用 HTTP Client 调试 REST API
            .csrf(csrf -> csrf.disable())

            // 请求授权规则
            .authorizeHttpRequests(auth -> auth
                // Customer API 需要认证
                .requestMatchers("/api/customers/**").authenticated()

                // 其他接口暂时允许访问
                .anyRequest().permitAll())

            // 使用 HTTP Basic 认证
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
     * MVP 开发阶段使用内存用户
     */
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.withUsername("victor").password("{noop}123456").roles("USER").build();

        return new InMemoryUserDetailsManager(user);
    }
}