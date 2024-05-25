package com.introme.user.entity;

import com.introme.oauth.SocialType;
import com.introme.oauth.userInfo.Oauth2UserInfo;
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

    public static IntroMeUser toEntity(SocialType socialType, Oauth2UserInfo oauth2UserInfo) {
        return IntroMeUser.builder()
                .socialType(socialType)
                .socialId(oauth2UserInfo.getId())
                .name(oauth2UserInfo.getName())
                .email(oauth2UserInfo.getEmail())
                .role(Role.User)
                .picture(oauth2UserInfo.getPicture())
                .build();
    }
}

