package com.introme.talent.entity;

import com.introme.company.entity.Permission;
import com.introme.talent.dto.request.TalentReqDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Talent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String keyword;

    private String description;

    private String icon;

    @Enumerated(EnumType.STRING)
    private Permission permission;

    private String baseUrl;

    @Builder
    public Talent(String keyword, String description, String icon, Permission permission, String baseUrl) {
        this.keyword = keyword;
        this.description = description;
        this.icon = icon;
        this.permission = permission;
        this.baseUrl = baseUrl;
    }

    public static Talent toEntity(TalentReqDTO talentReqDTO) {
        return Talent.builder()
                .keyword(talentReqDTO.getKeyword())
                .description(talentReqDTO.getDescription())
                .baseUrl(talentReqDTO.getBaseUrl())
                .build();
    }
}
