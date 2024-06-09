package com.introme.infrastructure.security.oauth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OAuth2ProviderUserResolver {

    public OAuth2ProviderUser resolve(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        OAuth2Provider oAuth2Provider = OAuth2Provider.from(userRequest.getClientRegistration().getClientId());
        return OAuth2ProviderUser.builder()
                .id(oAuth2User.getAttribute(StandardClaimNames.SUB))
                .userProviderType(oAuth2Provider.userProviderType())
                .email(oAuth2User.getAttribute(StandardClaimNames.EMAIL))
                .name(oAuth2User.getAttribute(StandardClaimNames.NAME))
                .build();
    }
}
