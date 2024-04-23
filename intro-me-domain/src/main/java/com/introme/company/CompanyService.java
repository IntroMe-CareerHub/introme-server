package com.introme.company;

import com.introme.company.dto.request.CompanyReqDTO;
import com.introme.company.dto.response.AllCompaniesResDTO;
import com.introme.company.dto.response.CompanyDetailResDTO;
import com.introme.company.dto.response.SubmitCompanyResDTO;
import com.introme.company.entity.Company;
import com.introme.company.entity.Permission;
import com.introme.company.repository.CompanyRepository;
import com.introme.talent.dto.request.TalentReqDTO;
import com.introme.talent.entity.Talent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company save(CompanyReqDTO companyReqDTO) {
        return companyRepository.save(Company.toEntity(companyReqDTO));
    }

    public Company submit(CompanyReqDTO companyReqDTO) {
        return companyRepository.save(Company.toTempEntity(companyReqDTO));
    }

    public Page<Company> findAllCompany(Pageable pageable) {
        return companyRepository.findAllByPermission(Permission.APPROVED, pageable);
    }

    public CompanyDetailResDTO findCompanyData(Long companyId) {
        return CompanyDetailResDTO.toResponseDTO(companyRepository.findById(companyId).orElseThrow());
    }

    public List<AllCompaniesResDTO> findCompanyByKeyword(String keyword) {
        return companyRepository.findByNameContaining(keyword).stream()
                .map(AllCompaniesResDTO::toResponseDTO)
                .toList();
    }

    public SubmitCompanyResDTO submitTalent(Long companyId, TalentReqDTO talentReqDTO) {
        Talent talent = Talent.toEntity(talentReqDTO);
        Company company = companyRepository.findById(companyId).orElseThrow();
        company.getTalents().add(talent);
        companyRepository.save(company);

        return SubmitCompanyResDTO.toResponseDTO(company);
    }
}

