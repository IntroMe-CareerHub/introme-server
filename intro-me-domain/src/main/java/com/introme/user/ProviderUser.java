package com.introme.user;

import com.introme.user.entity.Role;

public interface ProviderUser {
    String getId();

    UserProviderType userProviderType();

    String getEmail();

    String getName();
}