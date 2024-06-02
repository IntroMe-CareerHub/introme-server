package com.introme.user;

public interface ProviderUser {
    String getId();

    UserProviderType userProviderType();

    String getEmail();

    String getName();
}