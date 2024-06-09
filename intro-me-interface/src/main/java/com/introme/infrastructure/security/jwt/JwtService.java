package com.introme.infrastructure.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.introme.configuration.JwtConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    private final JwtConfiguration.JwtProperties jwtProperties;

    private static final String ACCESS_TOKEN_SUBJECT = "introme";
    private static final String EMAIL_CLAIM = "email";
    private static final String BEARER = "Bearer ";

    public String createAccessToken(String email) {
        return JWT.create()
                .withSubject(ACCESS_TOKEN_SUBJECT)
                .withExpiresAt(JwtSupport.createExpirationDate(jwtProperties.accessTokenExpirationPeriod()))
                .withClaim(EMAIL_CLAIM, email)
                .sign(Algorithm.HMAC512(jwtProperties.secretKey()));

    }

    public void setAccessToken(HttpServletResponse response, String accessToken) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader(jwtProperties.accessHeader(), BEARER + accessToken);
    }

    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(jwtProperties.accessHeader()))
                .filter(accessToken -> accessToken.startsWith(BEARER))
                .map(accessToken -> accessToken.replace(BEARER, ""));
    }

    public Optional<String> extractEmail(String accessToken) {
        try {
            return Optional.ofNullable(JWT.require(Algorithm.HMAC512(jwtProperties.secretKey()))
                    .build()
                    .verify(accessToken)
                    .getClaim(EMAIL_CLAIM)
                    .asString());
        } catch (Exception e) {
            log.error("액세스 토큰이 유효하지 않습니다.");
            return Optional.empty();
        }
    }

    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(jwtProperties.secretKey())).build().verify(token);
            log.info("유효한 토큰입니다. {}", token);
            return true;
        } catch (Exception e) {
            log.error("유효하지 않은 토큰입니다. {}", e.getMessage());
            return false;
        }
    }


    private static class JwtSupport {
        private static final Long currentSystemMillis = Clock.system(ZoneId.systemDefault()).millis();

        public static Date createExpirationDate(Long expirationPeriod) {
            return new Date(currentSystemMillis + expirationPeriod);
        }
    }
}
