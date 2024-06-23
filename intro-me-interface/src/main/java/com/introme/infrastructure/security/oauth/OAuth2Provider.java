package com.introme.infrastructure.security.oauth;

import com.introme.user.UserProviderType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public record OAuth2Provider(
        String registrationId,
        UserProviderType userProviderType
) {

    private enum OAuth2Providers {
        GOOGLE(new OAuth2Provider("google", UserProviderType.GOOGLE));

        public final OAuth2Provider oAuth2Provider;

        OAuth2Providers(OAuth2Provider oAuth2Provider) {
            this.oAuth2Provider = oAuth2Provider;
        }
    }

    private static final Map<String, OAuth2Provider> REGISTRATION_PROVIDERS = new LinkedHashMap<>();

    static {
        for (OAuth2Providers provider : OAuth2Providers.values()) {
            REGISTRATION_PROVIDERS.put(provider.oAuth2Provider.registrationId, provider.oAuth2Provider);
        }
    }

    public static OAuth2Provider from(String registrationId) {
        return Objects.requireNonNull(REGISTRATION_PROVIDERS.get(registrationId));
    }
}
