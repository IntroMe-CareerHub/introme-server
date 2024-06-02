package com.introme.user;

public enum UserProviderType {
    GOOGLE;

    public static UserProviderType from(String providerType) {
        return valueOf(providerType.toUpperCase());
    }
}
