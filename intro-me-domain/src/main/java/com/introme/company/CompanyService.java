package com.introme.company;

import com.introme.company.dto.request.CompanyReqDTO;
import com.introme.company.entity.Company;
import com.introme.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company save(CompanyReqDTO companyReqDTO) {
        return companyRepository.save(Company.toEntity(companyReqDTO));
    }

    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }
}
