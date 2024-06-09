package com.introme.configuration;

import com.introme.infrastructure.security.jwt.JwtAuthenticationFilter;
import com.introme.infrastructure.security.jwt.JwtService;
import com.introme.infrastructure.security.oauth.IntromeOAuth2UserService;
import com.introme.infrastructure.security.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.introme.infrastructure.security.oauth.handler.Oauth2AuthenticationSuccessHandler;
import com.introme.user.entity.Role;
import com.introme.user.service.UserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final IntromeOAuth2UserService intromeOAuth2UserService;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final Oauth2AuthenticationSuccessHandler oAuth2LoginSuccessHandler;
    private final JwtService jwtService;
    private final UserQuery userQuery;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
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
                        .failureHandler(oAuth2AuthenticationFailureHandler)
                        .userInfoEndpoint((userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(intromeOAuth2UserService))))
                .addFilterAfter(new JwtAuthenticationFilter(jwtService, userQuery), AuthenticationFilter.class);

        return httpSecurity.build();
    }
}