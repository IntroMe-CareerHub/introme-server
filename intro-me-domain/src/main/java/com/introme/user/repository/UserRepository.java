package com.introme.user.repository;

import com.introme.user.entity.IntroMeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<IntroMeUser, Long> {
    Optional<IntroMeUser> findByEmail(String email);
}
