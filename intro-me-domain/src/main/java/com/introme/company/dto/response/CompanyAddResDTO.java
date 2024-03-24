package com.introme.company.dto.response;

import com.introme.company.entity.Company;
import com.introme.talent.entity.Talent;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
public class CompanyAddResDTO {
    @NonNull
    private Company company;
    private List<Talent> talents;
}