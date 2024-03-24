package com.introme.company;

import com.introme.company.dto.request.CompanyAddReqDTO;
import com.introme.company.dto.request.CompanyReqDTO;
import com.introme.company.dto.response.CompanyAddResDTO;
import com.introme.company.entity.Company;
import com.introme.company.repository.CompanyRepository;
import com.introme.talent.entity.Talent;
import com.introme.talent.entity.TalentInfo;
import com.introme.talent.repository.TalentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final TalentRepository talentRepository;

    public Company save(CompanyReqDTO companyReqDTO) {
        return companyRepository.save(Company.toEntity(companyReqDTO));
    }

    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

    public CompanyAddResDTO addCompanyTalents(CompanyAddReqDTO companyAddReqDTO) {
        Company company = Company.toEntity(companyAddReqDTO);
        companyRepository.save(company);

        List<TalentInfo> talentInfos = companyAddReqDTO.getTalentInfo();
        List<Talent> talents = new ArrayList<>();

        for (TalentInfo talentInfo : talentInfos) {
            Talent talent = Talent.toEntity(talentInfo, company);
            talents.add(talentRepository.save(talent));
        }

        return new CompanyAddResDTO(company, talents);
    }
}
