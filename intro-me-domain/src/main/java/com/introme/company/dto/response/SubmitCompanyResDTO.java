package com.introme.company.dto.response;

import com.introme.company.entity.Company;
import com.introme.company.entity.CompanyInfo;
import com.introme.talent.entity.Talent;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
public class SubmitCompanyResDTO {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String image;

    @NotNull
    private String backgroundColor;

    private CompanyInfo companyInfo;

    private List<Talent> talents;

    @Builder
    public SubmitCompanyResDTO(Long id, String name, String image, String backgroundColor, CompanyInfo companyInfo, List<Talent> talents) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.backgroundColor = backgroundColor;
        this.companyInfo = companyInfo;
        this.talents = talents;
    }

    public static SubmitCompanyResDTO toResponseDTO(Company company) {
        return SubmitCompanyResDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .image(company.getImage())
                .backgroundColor(company.getBackgroundColor())
                .companyInfo(company.getCompanyInfo())
                .talents(company.getTalents())
                .build();
    }
}
