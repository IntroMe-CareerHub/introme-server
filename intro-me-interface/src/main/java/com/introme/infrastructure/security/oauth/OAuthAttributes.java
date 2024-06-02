package com.introme.infrastructure.security.oauth;

import com.introme.infrastructure.security.user.GoogleOAuth2UserInfo;
import com.introme.infrastructure.security.user.Oauth2UserInfo;
import com.introme.user.UserProviderType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private String nameAttributeKey;
    private Oauth2UserInfo oauth2UserInfo;

    @Builder
    public OAuthAttributes(String nameAttributeKey, Oauth2UserInfo oauth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oauth2UserInfo = oauth2UserInfo;
    }

    public static OAuthAttributes of(Pr  providerType, String usernameAttributeName, Map<String, Object> attributes) {
       if (providerType == UserProviderType.GOOGLE) {
           return ofGoogle(usernameAttributeName, attributes);
       }
       return null;
    }

    private static OAuthAttributes ofGoogle(String usernameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(usernameAttributeName)
                .oauth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                .build();
    }
}
