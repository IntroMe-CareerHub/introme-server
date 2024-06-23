package com.introme.infrastructure.security.oauth.handler;

import com.introme.infrastructure.security.jwt.JwtService;
import com.introme.infrastructure.security.oauth.IntromeOauth2User;
import com.introme.infrastructure.security.oauth.OAuth2ParameterServerRedirectStrategy;
import com.introme.user.entity.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final OAuth2ParameterServerRedirectStrategy redirectStrategy;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login 성공");
        IntromeOauth2User oauth2User = (IntromeOauth2User) authentication.getPrincipal();

        if (oauth2User.userContext().getRole() == Role.USER) {
            String accessToken = jwtService.createAccessToken(oauth2User.userContext().getEmail(), oauth2User.userContext().getId());
            String url = generateRedirectURI(accessToken).toString();
            redirectStrategy.sendRedirect(request, response, url);
        } else {
            redirectStrategy.sendRedirect(request, response, REDIRECT_URI);
        }

    }

    @Value("${introMe.redirectUrl}")
    private String REDIRECT_URI;

    private URI generateRedirectURI(String accessToken) {
        return UriComponentsBuilder.fromUriString(REDIRECT_URI)
                .queryParam(ACCESS_TOKEN_PARAM, URLEncoder.encode(accessToken, StandardCharsets.UTF_8))
                .build().toUri();
    }

    private static final String ACCESS_TOKEN_PARAM = "accessToken";

    private static final Logger log = LoggerFactory.getLogger(OAuth2AuthenticationFailureHandler.class);
}