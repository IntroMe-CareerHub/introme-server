package com.introme.talent.entity;

import com.introme.company.entity.Company;
import com.introme.talent.dto.TalentDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Talent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String keyword;

    private String description;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;

    @Builder
    public Talent(Long id, String keyword, String description, Company company) {
        this.id = id;
        this.keyword = keyword;
        this.description = description;
        this.company = company;
    }

    public static Talent toEntity(TalentDTO talentDTO, Company company) {
        return Talent.builder()
                .keyword(talentDTO.getKeyword())
                .description(talentDTO.getDescription())
                .company(company)
                .build();
    }
}
