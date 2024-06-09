package com.introme.user.service;

import com.introme.user.ProviderUser;
import com.introme.user.entity.Role;
import com.introme.user.entity.User;
import com.introme.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class UserCommand {

    private final UserRepository userRepository;

    public User createUser(ProviderUser providerUser, Role role) {
        User.ProviderKey providerKey = User.ProviderKey.of(providerUser.getId(), providerUser.userProviderType());
        User user = User.builder()
                .email(providerUser.getEmail())
                .key(providerKey)
                .role(role)
                .name(providerUser.getName())
                .build();

        return userRepository.save(user);
    }
}
