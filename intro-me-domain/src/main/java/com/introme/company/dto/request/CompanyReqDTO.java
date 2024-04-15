package com.introme.company.dto.request;

import com.introme.company.entity.CompanyInfo;
import com.introme.talent.dto.request.TalentReqDTO;
import lombok.*;

import java.util.List;

@Getter
public class CompanyReqDTO {
    @NonNull
    private String name;

    @NonNull
    private String image;

    private CompanyInfo companyInfo;

    private List<TalentReqDTO> talents;
}
