package com.introme.company.dto.response;

import com.introme.company.entity.Company;
import com.introme.company.entity.CompanyInfo;
import com.introme.company.entity.TempCompany;
import com.introme.talent.entity.Talent;
import com.introme.talent.entity.TempTalent;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class TempCompanyResDTO {
    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String image;

    private CompanyInfo information;

    private List<TempTalent> talents;

    @Builder
    public TempCompanyResDTO(Long id, String name, String image, CompanyInfo information, List<TempTalent> talents) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.information = information;
        this.talents = talents;
    }

    public static TempCompanyResDTO toResponseDTO(TempCompany tempCompany) {
        return TempCompanyResDTO.builder()
                .id(tempCompany.getId())
                .name(tempCompany.getName())
                .image(tempCompany.getImage())
                .information(tempCompany.getInformation())
                .talents(tempCompany.getTempTalents())
                .build();
    }
}
