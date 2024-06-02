package com.introme.infrastructure.security.oauth;

import com.introme.user.ProviderUser;
import com.introme.user.entity.User;
import com.introme.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OAuth2UserResolver {
    private final UserService userService;

    public User resolve(ProviderUser providerUser) {

    }

}
