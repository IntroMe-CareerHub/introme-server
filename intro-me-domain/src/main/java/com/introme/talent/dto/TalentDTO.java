package com.introme.talent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TalentDTO {
    @NonNull
    private String keyword;

    private String description;

    @NonNull
    private Long companyId;
}
