package com.introme.infrastructure.security.user;

import com.introme.user.entity.Role;

public interface UserContext {
    Long getId();

    String getEmail();

    Provider getProvider();

    Role getRole();

    interface Provider {
        String getId();

        String getType();
    }
}