package com.introme.infrastructure.security.oauth.handler;

import com.introme.infrastructure.security.jwt.JwtService;
import com.introme.infrastructure.security.oauth.IntromeOauth2User;
import com.introme.user.entity.Role;
import com.introme.user.service.UserQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@RequiredArgsConstructor
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final UserQuery userQuery;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login 성공");
        IntromeOauth2User oauth2User = (IntromeOauth2User) authentication.getPrincipal();

        if (oauth2User.userContext().getRole() == Role.USER) {
            loginSuccess(response, oauth2User);
        }
    }

    private void loginSuccess(HttpServletResponse response, IntromeOauth2User oAuth2User) throws IOException {
        String accessToken = jwtService.createAccessToken(oAuth2User.userContext().getEmail());
        jwtService.setAccessTokenAtHeader(response, accessToken);
    }

    private static final Logger log = LoggerFactory.getLogger(OAuth2LoginFailureHandler.class);
}