package com.introme.infrastructure.security.user;

import com.introme.user.ProviderUser;
import com.introme.user.UserProviderType;
import com.introme.user.entity.Role;

record GoogleProviderUser(
        String id,
        UserProviderType providerType,
        String email,
        String name,
        Role role
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

    @Override
    public Role getRole() {
        return this.role;
    }
}