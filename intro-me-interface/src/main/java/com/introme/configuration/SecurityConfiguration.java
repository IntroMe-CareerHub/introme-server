package com.introme.configuration;

import com.introme.infrastructure.security.jwt.JwtAuthenticationFilter;
import com.introme.infrastructure.security.jwt.JwtService;
import com.introme.infrastructure.security.oauth.IntromeOAuth2UserService;
import com.introme.infrastructure.security.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.introme.infrastructure.security.oauth.handler.Oauth2AuthenticationSuccessHandler;
import com.introme.user.entity.Role;
import com.introme.user.service.UserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final IntromeOAuth2UserService intromeOAuth2UserService;

    private final JwtService jwtService;
    private final UserQuery userQuery;

    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final Oauth2AuthenticationSuccessHandler oAuth2LoginSuccessHandler;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .securityMatcher("/**")
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .headers((headerConfig) -> headerConfig.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::disable
                ))
                .authorizeHttpRequests((authorizeRequest) ->
                        authorizeRequest
                                .requestMatchers("/", "/h2-console/**", "/login/**").permitAll()
                                .requestMatchers("/admin/**").hasAnyRole(Role.ADMIN.name())
                                .anyRequest().authenticated()
                )
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig ->
                                        userInfoEndpointConfig.userService(intromeOAuth2UserService)
                                )
                        )
                        .successHandler(oAuth2LoginSuccessHandler)
                        .failureHandler(oAuth2AuthenticationFailureHandler)
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, userQuery), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}