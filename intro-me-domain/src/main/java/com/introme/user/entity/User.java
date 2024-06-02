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

    @Column
    private UserProviderType userProviderType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}

