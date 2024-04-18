package com.introme.company.dto.request;

import com.introme.company.entity.CompanyInfo;
import com.introme.talent.dto.request.TalentReqDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
public class CompanyReqDTO {
    @NotNull
    private String name;

    @NotNull
    private String image;

    private CompanyInfo companyInfo;

    private List<TalentReqDTO> talents;
}
