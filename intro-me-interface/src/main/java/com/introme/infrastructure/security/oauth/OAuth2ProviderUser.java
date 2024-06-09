package com.introme.infrastructure.security.oauth;

import com.introme.user.ProviderUser;
import com.introme.user.UserProviderType;
import lombok.Builder;

@Builder
public record OAuth2ProviderUser(
        String id,
        UserProviderType userProviderType,
        String email,
        String name
) implements ProviderUser {

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public UserProviderType userProviderType() {
        return this.userProviderType;
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
