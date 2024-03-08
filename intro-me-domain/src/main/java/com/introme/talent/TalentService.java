package com.introme.talent;

import com.introme.company.entity.Company;
import com.introme.company.repository.CompanyRepository;
import com.introme.talent.dto.TalentDTO;
import com.introme.talent.entity.Talent;
import com.introme.talent.repository.TalentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TalentService {
    private TalentRepository talentRepository;
    private final CompanyRepository companyRepository;

    public Talent save(TalentDTO talentDTO) {
        Company company = companyRepository.findById(talentDTO.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("Company not found with this id:" + talentDTO.getCompanyId()));

        return talentRepository.save(Talent.toEntity(talentDTO, company));
    }
}
