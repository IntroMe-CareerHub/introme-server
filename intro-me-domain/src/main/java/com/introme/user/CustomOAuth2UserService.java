package com.introme.user;

import com.introme.oauth.SocialType;
import com.introme.user.entity.IntroMeUser;
import com.introme.oauth.OAuthAttributes;
import com.introme.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private static final Logger log = LoggerFactory.getLogger(CustomOAuth2UserService.class);
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("loadUser() 실행 - OAuth2 로그인 요청 진입");
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        SocialType socialType = SocialType.GOOGLE;
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(socialType, userNameAttributeName, oAuth2User.getAttributes());

        IntroMeUser createdUser = getUser(attributes, socialType);

        return new CustomOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(createdUser.getRole().getKey())),
                oAuth2User.getAttributes(),
                attributes.getNameAttributeKey(),
                createdUser.getEmail(),
                createdUser.getRole());
    }

    private IntroMeUser getUser(OAuthAttributes attributes, SocialType socialType) {
        IntroMeUser findUser = userRepository.findBySocialTypeAndSocialId(socialType, attributes.getOauth2UserInfo().getId()).orElse(null);
        if (findUser == null) {
            return saveUser(attributes, socialType);
        }
        return findUser;
    }

    private IntroMeUser saveUser(OAuthAttributes attributes, SocialType socialType) {
        IntroMeUser user = attributes.toEntity(socialType, attributes.getOauth2UserInfo());

        return userRepository.save(user);
    }

}
