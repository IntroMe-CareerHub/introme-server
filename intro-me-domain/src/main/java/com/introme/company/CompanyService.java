package com.introme.company;

import com.introme.company.dto.CompanyDTO;
import com.introme.company.entity.Company;
import com.introme.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company save(CompanyDTO companyDTO) {
        return companyRepository.save(Company.toEntity(companyDTO));
    }

}
