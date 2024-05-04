package com.introme.config.auth;

import com.introme.user.CustomOAuth2UserService;
import com.introme.user.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // TODO: 코드 돌아가는 흐름 깨우치기 & 리팩토링
        httpSecurity
                .csrf(CsrfConfigurer::disable)
                .headers((headerConfig) -> headerConfig.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::disable
                ))
                .authorizeHttpRequests((authorizeRequest) ->
                        authorizeRequest
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/api/v1/company/**").hasAnyRole(Role.User.name())
                                .requestMatchers("/admin/**").hasAnyRole(Role.ADMIN.name())
                                .anyRequest().authenticated())
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)))
                        .defaultSuccessUrl("/oauth/login"))
                .logout((logoutConfig) ->
                        logoutConfig.logoutSuccessUrl("/"));

        return httpSecurity.build();
    }
}
