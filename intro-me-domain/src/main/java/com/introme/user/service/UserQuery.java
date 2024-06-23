package com.introme.user.service;

import com.introme.user.UserProviderType;
import com.introme.user.entity.User;
import com.introme.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQuery {

    private final UserRepository userRepository;

    public User getUserOrNull(String email, UserProviderType userProviderType) {
        return userRepository.findByEmailAndProviderType(email, userProviderType);
    }

    public User getUser(Long userId, String email) {
        User user = userRepository.findByIdAndEmail(userId, email);
        if (user == null) throw new NoSuchElementException("해당 User 가 존재하지 않음");
        return user;
    }
}
