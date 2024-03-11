package com.introme.talent.entity;

import com.introme.company.entity.Company;
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

    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;

    @Builder
    public Talent(String keyword, String description, Company company) {
        this.keyword = keyword;
        this.description = description;
        this.company = company;
    }

    public static Talent toEntity(TalentReqDTO talentReqDTO, Company company) {
        return Talent.builder()
                .keyword(talentReqDTO.getKeyword())
                .description(talentReqDTO.getDescription())
                .company(company)
                .build();
    }
}
