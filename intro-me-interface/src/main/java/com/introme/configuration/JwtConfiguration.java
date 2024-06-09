package com.introme.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(JwtConfiguration.JwtProperties.class)
@Configuration
public class JwtConfiguration {

    @ConfigurationProperties(prefix = "server.jwt")
    public record JwtProperties(
            String secretKey,
            Long accessTokenExpirationPeriod,
            String accessHeader
    ) {
    }
}
