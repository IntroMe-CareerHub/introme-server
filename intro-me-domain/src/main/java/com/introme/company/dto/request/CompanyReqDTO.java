package com.introme.company.dto.request;

import com.introme.company.entity.CompanyInfo;
import lombok.*;

@Getter
public class CompanyReqDTO {
    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String image;

    private CompanyInfo information;
}
