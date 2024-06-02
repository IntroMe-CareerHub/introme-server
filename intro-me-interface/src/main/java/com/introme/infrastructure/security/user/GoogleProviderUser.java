package com.introme.infrastructure.security.user;

import com.introme.user.ProviderUser;
import com.introme.user.UserProviderType;

record GoogleProviderUser(
        String id,
        UserProviderType providerType,
        String email,
        String name
) implements ProviderUser {


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public UserProviderType userProviderType() {
        return this.providerType;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getName() {
        return this.name;
    }
}