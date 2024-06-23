package com.introme.user.service;

import com.introme.user.ProviderUser;
import com.introme.user.UserProviderType;
import com.introme.user.entity.User;
import com.introme.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;




}
