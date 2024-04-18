package com.introme.company.repository;

import com.introme.company.entity.Company;
import com.introme.company.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByNameContaining(String keyword);

    Page<Company> findAllByPermission(Permission permission, Pageable pageable);
}
