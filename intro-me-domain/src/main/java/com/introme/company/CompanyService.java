package com.introme.company;

import com.introme.company.dto.request.CompanyReqDTO;
import com.introme.company.dto.response.CompanyResDTO;
import com.introme.company.entity.Company;
import com.introme.company.entity.Permission;
import com.introme.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public List<Company> findAllCompany() {
        // find only approved company data
        return companyRepository.findByPermission(Permission.APPROVED);
    }

    public CompanyResDTO findCompanyData(Long companyId) {
        return CompanyResDTO.toResponseDTO(companyRepository.findById(companyId).orElseThrow());
    }

    public List<CompanyResDTO> findCompanyByKeyword(String keyword) {
        return companyRepository.findByNameContaining(keyword).stream()
                .map(CompanyResDTO::toResponseDTO)
                .toList();
    }
}

