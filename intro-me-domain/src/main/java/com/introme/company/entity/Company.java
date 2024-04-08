package com.introme.company.entity;

import com.introme.company.CompanyInfoJsonConverter;
import com.introme.company.dto.request.CompanyReqDTO;
import com.introme.talent.entity.Talent;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId")
    private List<Talent> talents = new ArrayList<>();

    public Company(String name, String image, CompanyInfo information) {
        this.name = name;
        this.image = image;
        this.information = information;
    }

    @Builder
    public Company(Long id, String name, String image, CompanyInfo information, List<Talent> talents) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.information = information;
        this.talents = talents;
    }

    public static Company toEntity(CompanyReqDTO companyReqDTO) {
        List<Talent> talentList = companyReqDTO.getTalents().stream()
                .map(Talent::toEntity)
                .toList();

        return Company.builder()
                .name(companyReqDTO.getName())
                .image(companyReqDTO.getImage())
                .information(companyReqDTO.getInformation())
                .talents(talentList)
                .build();
    }
}
