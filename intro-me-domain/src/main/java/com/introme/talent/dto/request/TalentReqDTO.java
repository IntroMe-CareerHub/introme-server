package com.introme.talent.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
public class TalentReqDTO {
    @NonNull
    private String keyword;

    private String description;

    @NonNull
    private Long companyId;
}
