package com.introme.infrastructure.security.user;

import lombok.Builder;

@Builder
public record ProviderImpl(
        String id,
        String type
) implements UserContext.Provider {

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
