package com.introme.company.dto.response;

import com.introme.company.entity.Company;
import com.introme.company.entity.CompanyInfo;
import lombok.*;

@Getter
public class CompanyResDTO {

    @NonNull
    private String name;

    @NonNull
    private String image;

    private CompanyInfo information;

    @Builder
    public CompanyResDTO(String name, String image, CompanyInfo information) {
        this.name = name;
        this.image = image;
        this.information = information;
    }

    public static CompanyResDTO toResponseDTO(Company company) {
        return CompanyResDTO.builder()
                .name(company.getName())
                .image(company.getImage())
                .information(company.getInformation())
                .build();
    }
}
