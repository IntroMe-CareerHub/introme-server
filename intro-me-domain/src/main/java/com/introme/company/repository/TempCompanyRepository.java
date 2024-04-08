package com.introme.company.repository;

import com.introme.company.entity.TempCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempCompanyRepository extends JpaRepository<TempCompany, Long> {

}
