package com.introme.company;


import com.introme.company.dto.CompanyDTO;
import com.introme.company.entity.Company;
import com.introme.talent.TalentService;
import com.introme.talent.dto.TalentDTO;
import com.introme.talent.entity.Talent;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(title = "기업별 인재상 리스트 API 명세서",
                version = "v1"
            )
        )
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CompanyRestController {

    private final CompanyService companyService;
    private final TalentService talentService;

    @Tag(name = "DB 세팅")
    @Operation(
            summary = "Company 데이터 저장 API",
            description = "기업 정보 데이터를 DB에 저장합니다.",
            tags = "DB 세팅"
    )
    @PostMapping(value = "/company/save")
    public ResponseEntity<Company> saveCompanyData(@RequestBody CompanyDTO companyDTO) {
        var data = companyService.save(companyDTO);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Talent 데이터 저장하기 API",
            description = "인재상 데이터를 DB에 저장합니다.",
            tags = "DB 세팅"
    )
    @PostMapping(value = "/talent/save")
    public ResponseEntity<Talent> saveTalentData(@RequestBody TalentDTO talentDTO) {
        var data = talentService.save(talentDTO);
        return ResponseEntity.ok(data);
    }

    @Tag(name = "기업별 인재상 리스트 API")
    @Operation(
            summary = "전체 기업 조회하기 API",
            description = "전체 기업의 데이터를 조회합니다.",
            tags = "기업별 인재상 리스트 API"

    )
    @GetMapping(value = "/company/list")
    public ResponseEntity<List<Company>> getCompanyList() {
        var data = companyService.findAllCompany();
        System.out.println(data);
        return ResponseEntity.ok(data);
    }
}
