package com.introme.company.dto;

import lombok.*;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class CompanyDTO {
    @NonNull
    private String name;

    @NonNull
    private String image;

    private Map<String, Object> information;
}
