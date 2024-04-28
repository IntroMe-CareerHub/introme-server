package com.introme.company.repository;

import com.introme.company.entity.Company;
import com.introme.company.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Page<Company> findByNameContaining(String companyName, Pageable pageable);

    Page<Company> findAllByPermission(Permission permission, Pageable pageable);
}
