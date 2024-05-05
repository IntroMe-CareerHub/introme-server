package com.introme.user.service;

import com.introme.oauth.SocialType;
import com.introme.oauth.userInfo.Oauth2UserInfo;
import com.introme.user.entity.IntroMeUser;
import com.introme.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public IntroMeUser getUser(Oauth2UserInfo oauth2UserInfo, SocialType socialType) {
        IntroMeUser findUser = userRepository.findBySocialTypeAndSocialId(socialType, oauth2UserInfo.getId()).orElse(null);
        if (findUser == null) {
            return saveUser(oauth2UserInfo, socialType);
        }
        return findUser;
    }

    private IntroMeUser saveUser(Oauth2UserInfo oauth2UserInfo, SocialType socialType) {
        IntroMeUser user = IntroMeUser.toEntity(socialType, oauth2UserInfo);

        return userRepository.save(user);
    }
}
