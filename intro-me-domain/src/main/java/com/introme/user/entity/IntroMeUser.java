package com.introme.user.entity;

import com.introme.oauth.SocialType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class IntroMeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;
    private String socialId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


//    @Builder
//    public IntroMeUser(Long id, String name, String email, String password, String picture, Role role) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.picture = picture;
//        this.role = role;
//}

public IntroMeUser update(String name) {
    this.name = name;

    return this;
}
}

