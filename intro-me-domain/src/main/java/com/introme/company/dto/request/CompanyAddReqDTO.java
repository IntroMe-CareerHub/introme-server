package com.introme.company.dto.request;

import com.introme.company.entity.CompanyInfo;
import com.introme.talent.entity.TalentInfo;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class CompanyAddReqDTO {
    @NonNull
    private String name;
    private String image;
    private CompanyInfo companyInfo;
    private List<TalentInfo> talentInfo;
}
