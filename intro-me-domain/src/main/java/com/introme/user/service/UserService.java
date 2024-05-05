package com.introme.user.service;

import com.introme.oauth.OAuthAttributes;
import com.introme.oauth.SocialType;
import com.introme.user.entity.IntroMeUser;
import com.introme.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public IntroMeUser getUser(OAuthAttributes attributes, SocialType socialType) {
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
