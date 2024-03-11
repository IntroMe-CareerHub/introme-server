package com.introme.company.entity;

import com.introme.company.CompanyInfoJsonConverter;
import com.introme.company.dto.request.CompanyReqDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column
    private String image;

    @Convert(converter = CompanyInfoJsonConverter.class)
    @Column
    private CompanyInfo information;

    public Company(String name, String image, CompanyInfo information) {
        this.name = name;
        this.image = image;
        this.information = information;
    }

    @Builder
    public Company(Long id, String name, String image, CompanyInfo information) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.information = information;
    }

    public static Company toEntity(CompanyReqDTO companyReqDTO) {
        return Company.builder()
                .name(companyReqDTO.getName())
                .image(companyReqDTO.getImage())
                .information(companyReqDTO.getInformation())
                .build();
    }
}
