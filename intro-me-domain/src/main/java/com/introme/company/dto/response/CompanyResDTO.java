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
public class CompanyResDTO {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String image;

    @NotNull
    private String backgroundColor;

    private CompanyInfo companyInfo;

    private LocalDateTime updatedAt;

    private List<Talent> talents;

    @Builder
    public CompanyResDTO(Long id, String name, String image, String backgroundColor, CompanyInfo companyInfo, List<Talent> talents, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.backgroundColor = backgroundColor;
        this.companyInfo = companyInfo;
        this.talents = talents;
        this.updatedAt = updatedAt;
    }

    public static CompanyResDTO toResponseDTO(Company company) {
        List<Talent> talentList = company.getTalents().stream()
                .filter(talent -> talent.getPermission() == Permission.APPROVED)
                .toList();


        return CompanyResDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .image(company.getImage())
                .backgroundColor(company.getBackgroundColor())
                .companyInfo(company.getCompanyInfo())
                .talents(talentList)
                .updatedAt(company.getUpdatedAt())
                .build();
    }
}
