package com.introme.company;


import com.introme.company.dto.CompanyDTO;
import com.introme.company.entity.Company;
import com.introme.talent.TalentService;
import com.introme.talent.dto.TalentDTO;
import com.introme.talent.entity.Talent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CompanyRestController {

    private final CompanyService companyService;
    private final TalentService talentService;

    @PostMapping(value = "/company/save")
    public ResponseEntity<Company> saveCompanyData(@RequestBody CompanyDTO companyDTO) {
        var data = companyService.save(companyDTO);
        return ResponseEntity.ok(data);
    }

    @PostMapping(value = "/talent/save")
    public ResponseEntity<Talent> saveTalentData(@RequestBody TalentDTO talentDTO) {
        var data = talentService.save(talentDTO);
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/company/list")
    public ResponseEntity<List<Company>> getCompanyList() {
        var data = companyService.findAllCompany();
        System.out.println(data);
        return ResponseEntity.ok(data);
    }
}
