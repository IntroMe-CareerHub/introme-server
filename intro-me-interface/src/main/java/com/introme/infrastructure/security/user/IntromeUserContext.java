package com.introme.infrastructure.security.user;

import com.introme.infrastructure.security.oauth.OAuth2ProviderUser;
import com.introme.user.entity.Role;
import com.introme.user.entity.User;
import lombok.Builder;

@Builder
public record IntromeUserContext(
        Long id,
        String email,
        Provider provider,
        Role role
) implements UserContext {

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public Provider getProvider() {
        return this.provider;
    }

    @Override
    public Role getRole() {
        return this.role;
    }

    public static IntromeUserContext of(User user, OAuth2ProviderUser providerUser) {
        return IntromeUserContext.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .provider(
                        ProviderImpl.builder()
                                .id(providerUser.id())
                                .type(providerUser.userProviderType().name())
                                .build()
                ).build();
    }
}
