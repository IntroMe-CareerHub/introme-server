package com.introme.talent.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TalentReqDTO {
    @NotNull
    private String keyword;
    @NotNull
    private String description;
    private String baseUrl;
}
