package com.introme.configuration;

import com.introme.infrastructure.security.jwt.JwtAuthenticationProcessingFilter;
import com.introme.infrastructure.security.jwt.JwtService;
import com.introme.infrastructure.security.oauth.CustomOAuth2UserService;
import com.introme.infrastructure.security.oauth.OAuth2UserAccountService;
import com.introme.infrastructure.security.oauth.handler.OAuth2LoginFailureHandler;
import com.introme.infrastructure.security.oauth.handler.OAuth2LoginSuccessHandler;
import com.introme.user.entity.Role;
import com.introme.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .headers((headerConfig) -> headerConfig.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::disable
                ))
                .authorizeHttpRequests((authorizeRequest) ->
                        authorizeRequest
                                .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/favicon.ico", "/h2-console/**", "/login").permitAll()
                                .requestMatchers("/admin/**").hasAnyRole(Role.ADMIN.name())
                                .anyRequest().authenticated())
                .oauth2Login((oauth2) -> oauth2
                        .successHandler(oAuth2LoginSuccessHandler)
                        .failureHandler(oAuth2LoginFailureHandler)
                        .userInfoEndpoint((userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))))
                .addFilterBefore(jwtAuthenticationProcessingFilter(), LogoutFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public OAuth2UserAccountService oAuth2UserAccountService() {
        return
    }

    @Bean
    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
        return new JwtAuthenticationProcessingFilter(jwtService, userRepository);
    }
}