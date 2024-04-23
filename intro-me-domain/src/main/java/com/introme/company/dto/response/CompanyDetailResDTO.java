package com.introme.company.dto.response;

import com.introme.company.entity.Company;
import com.introme.company.entity.CompanyInfo;
import com.introme.company.entity.Permission;
import com.introme.talent.entity.Talent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CompanyDetailResDTO {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String image;

    @NotNull
    private String identityColor;

    private CompanyInfo companyInfo;

    private LocalDateTime updatedAt;

    private List<Talent> talents;

    @Builder
    public CompanyDetailResDTO(Long id, String name, String image, String identityColor, CompanyInfo companyInfo, List<Talent> talents, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.identityColor = identityColor;
        this.companyInfo = companyInfo;
        this.talents = talents;
        this.updatedAt = updatedAt;
    }

    public static CompanyDetailResDTO toResponseDTO(Company company) {
        List<Talent> talentList = company.getTalents().stream()
                .filter(talent -> talent.getPermission() == Permission.APPROVED)
                .toList();


        return CompanyDetailResDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .image(company.getImage())
                .identityColor(company.getIdentityColor())
                .companyInfo(company.getCompanyInfo())
                .talents(talentList)
                .updatedAt(company.getUpdatedAt())
                .build();
    }
}
