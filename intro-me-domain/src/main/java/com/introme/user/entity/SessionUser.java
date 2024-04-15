package com.introme.user.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(IntroMeUser user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
