package com.introme.talent.entity;

import com.introme.talent.dto.request.TalentReqDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class TempTalent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String keyword;
    private String description;

    @Builder
    public TempTalent(String keyword, String description) {
        this.keyword = keyword;
        this.description = description;
    }
    public static TempTalent toEntity(TalentReqDTO talentReqDTO) {
        return TempTalent.builder()
                .keyword(talentReqDTO.getKeyword())
                .description(talentReqDTO.getDescription())
                .build();
    }
}
