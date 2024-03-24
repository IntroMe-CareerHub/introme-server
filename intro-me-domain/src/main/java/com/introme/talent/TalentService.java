package com.introme.talent;

import com.introme.company.entity.Company;
import com.introme.company.repository.CompanyRepository;
import com.introme.talent.dto.request.TalentReqDTO;
import com.introme.talent.dto.response.TalentResDTO;
import com.introme.talent.entity.Talent;
import com.introme.talent.repository.TalentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TalentService {
    private TalentRepository talentRepository;
    private final CompanyRepository companyRepository;

    public Talent save(TalentReqDTO talentReqDTO) {
        Company company = companyRepository.findById(talentReqDTO.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("Company not found with this id:" + talentReqDTO.getCompanyId()));

        return talentRepository.save(Talent.toEntity(talentReqDTO, company));
    }

    public List<TalentResDTO> getTalentsByCompanyId(Long companyId) {
        return talentRepository.findByCompanyId(companyId);
    }
}
