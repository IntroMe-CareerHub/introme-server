package com.introme.company;

import com.introme.company.dto.request.CompanyReqDTO;
import com.introme.company.dto.response.CompanyResDTO;
import com.introme.company.entity.Company;
import com.introme.company.entity.TempCompany;
import com.introme.company.repository.CompanyRepository;
import com.introme.company.repository.TempCompanyRepository;
import com.introme.talent.dto.response.TalentResDTO;
import com.introme.talent.entity.Talent;
import com.introme.talent.repository.TalentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final TempCompanyRepository tempCompanyRepository;

    public Company save(CompanyReqDTO companyReqDTO) {
        return companyRepository.save(Company.toEntity(companyReqDTO));
    }

    public TempCompany submit(CompanyReqDTO companyReqDTO) {
        return tempCompanyRepository.save(TempCompany.toEntity(companyReqDTO));
    }

    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

//    public List<TalentResDTO> findCompanyTalents(Long companyId) {
//        List<Talent> talentList;
//        Company company = companyRepository.findById(companyId).orElse(null);
//        if (company != null) {
//            talentList = company.getTalents();
//            System.out.println(talentList);
//
//            return talentList.stream()
//                    .map(TalentResDTO::toResponseDTO)
//                    .toList();
//        }
//        log.error("---> Company data is a null value.");
//        return null;
//    }

    public CompanyResDTO findCompanyData(Long companyId) {
        return CompanyResDTO.toResponseDTO(companyRepository.findById(companyId).orElseThrow());
    }
}

