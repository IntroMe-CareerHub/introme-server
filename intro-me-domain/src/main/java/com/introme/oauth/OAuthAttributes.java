package com.introme.oauth;

import com.introme.oauth.userInfo.GoogleOAuth2UserInfo;
import com.introme.oauth.userInfo.Oauth2UserInfo;
import com.introme.user.entity.IntroMeUser;
import com.introme.user.entity.Role;
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

    public static OAuthAttributes of(SocialType socialType, String usernameAttributeName, Map<String, Object> attributes) {
       if (socialType == SocialType.GOOGLE) {
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
