package com.introme.user.repository;


import com.introme.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

//    Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
