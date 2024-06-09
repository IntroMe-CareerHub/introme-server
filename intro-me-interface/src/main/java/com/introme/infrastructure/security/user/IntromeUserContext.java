package com.introme.infrastructure.security.user;

public record IntromeUserContext(
        Long id,
        String email,
        Provider provider
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
}
