package com.introme.user.entity;

import com.introme.user.UserProviderType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Embedded
    private ProviderKey key;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @Embeddable
    @NoArgsConstructor
    public static class ProviderKey {
        @Enumerated(EnumType.STRING)
        @Column(name = "providerType")
        UserProviderType type;
        @Column(name = "providerAccountId")
        String id;

        public ProviderKey(UserProviderType providerType, String providerAccountId) {
            this.type = providerType;
            this.id = providerAccountId;
        }

        public static ProviderKey of(String providerAccountId, UserProviderType providerType) {
            return new ProviderKey(providerType, providerAccountId);
        }
    }

}

