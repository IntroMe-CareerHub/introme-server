package com.introme.company.dto.response;

import com.introme.company.entity.Company;
import com.introme.company.entity.CompanyInfo;
import lombok.*;

@Getter
public class CompanyResDTO {
    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String image;

    private CompanyInfo information;

    @Builder
    public CompanyResDTO(Long id, String name, String image, CompanyInfo information) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.information = information;
    }

    public static CompanyResDTO toResponseDTO(Company company) {
        return CompanyResDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .image(company.getImage())
                .information(company.getInformation())
                .build();
    }
}
