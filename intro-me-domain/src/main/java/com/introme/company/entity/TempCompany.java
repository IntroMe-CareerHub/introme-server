package com.introme.company.entity;

import com.introme.company.CompanyInfoJsonConverter;
import com.introme.company.dto.request.CompanyReqDTO;
import com.introme.talent.entity.TempTalent;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class TempCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String image;

    @Convert(converter = CompanyInfoJsonConverter.class)
    private CompanyInfo information;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId")
    private List<TempTalent> tempTalents = new ArrayList<>();


    @Builder
    public TempCompany(String name, String image, CompanyInfo information, List<TempTalent> tempTalents) {
        this.name = name;
        this.image = image;
        this.information = information;
        this.tempTalents = tempTalents;
    }


    public static TempCompany toEntity(CompanyReqDTO companyReqDTO) {
        List<TempTalent> talentList = companyReqDTO.getTalents().stream()
                .map(TempTalent::toEntity)
                .toList();

        return TempCompany.builder()
                .name(companyReqDTO.getName())
                .image(companyReqDTO.getImage())
                .information(companyReqDTO.getInformation())
                .tempTalents(talentList)
                .build();
    }

}
