package com.introme.company.dto.response;

import com.introme.company.entity.Company;
import com.introme.company.entity.CompanyInfo;
import com.introme.talent.entity.Talent;
import lombok.*;

import java.util.List;

@Getter
public class CompanyResDTO {
    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String image;

    private CompanyInfo information;

    private List<Talent> talents;

    @Builder
    public CompanyResDTO(Long id, String name, String image, CompanyInfo information, List<Talent> talents) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.information = information;
        this.talents = talents;
    }

    public static CompanyResDTO toResponseDTO(Company company) {
        return CompanyResDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .image(company.getImage())
                .information(company.getInformation())
                .talents(company.getTalents())
                .build();
    }
}
