package com.introme.talent.repository;

import com.introme.talent.dto.response.TalentResDTO;
import com.introme.talent.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalentRepository extends JpaRepository<Talent, Long> {
    List<TalentResDTO> findByCompanyId(Long companyId);
}
