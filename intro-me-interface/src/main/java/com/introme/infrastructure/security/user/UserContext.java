package com.introme.infrastructure.security.user;

public interface UserContext {
    Long getId();

    String getEmail();

    Provider getProvider();


    interface Provider {
        String getId();

        String getType();
    }
}