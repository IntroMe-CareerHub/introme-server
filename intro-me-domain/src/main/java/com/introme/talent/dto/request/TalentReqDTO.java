package com.introme.talent.dto.request;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class TalentReqDTO {
    @NonNull
    private String keyword;

    private String description;

    @NonNull
    private Long companyId;
}
