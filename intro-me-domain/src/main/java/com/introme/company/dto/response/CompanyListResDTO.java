package com.introme.company.dto.response;

import com.introme.company.entity.Company;
import com.introme.company.entity.Permission;
import com.introme.talent.dto.response.TalentResDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CompanyListResDTO {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String image;

    @NotNull
    private String location;
    private String url;
    private List<TalentResDTO> talents;


    @Builder
    public CompanyListResDTO(Long id, String name, String image, String location, String url, List<TalentResDTO> talents) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.location = location;
        this.url = url;
        this.talents = talents;
    }


    public static CompanyListResDTO toResponseDTO(Company company) {
        List<TalentResDTO> talentResDTOS = company.getTalents().stream()
                .filter(talent -> talent.getPermission() == Permission.APPROVED)
                .map(TalentResDTO::toResponseDTO)
                .toList();

        return CompanyListResDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .image(company.getImage())
                .location(company.getCompanyInfo().getLocation())
                .url(company.getCompanyInfo().getUrl())
                .talents(talentResDTOS)
                .build();
    }
}
