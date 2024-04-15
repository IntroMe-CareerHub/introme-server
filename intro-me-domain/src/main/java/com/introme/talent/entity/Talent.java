package com.introme.talent.entity;

import com.introme.talent.dto.request.TalentReqDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Builder
    public Talent(String keyword, String description) {
        this.keyword = keyword;
        this.description = description;
    }

    public static Talent toEntity(TalentReqDTO talentReqDTO) {
        return Talent.builder()
                .keyword(talentReqDTO.getKeyword())
                .description(talentReqDTO.getDescription())
                .build();
    }
}
