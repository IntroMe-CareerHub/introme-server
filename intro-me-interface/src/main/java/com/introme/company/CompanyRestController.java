package com.introme.company;


import com.introme.company.dto.request.CompanyReqDTO;
import com.introme.company.dto.response.CompanyResDTO;
import com.introme.talent.TalentService;
import com.introme.talent.dto.request.TalentReqDTO;
import com.introme.talent.dto.response.TalentResDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @PostMapping(value = "/company/save", produces = "application/json")
    public ResponseEntity<CompanyResDTO> saveCompanyData(@RequestBody CompanyReqDTO companyReqDTO) {
        var data = companyService.save(companyReqDTO);
        var res = CompanyResDTO.toResponseDTO(data);
        return ResponseEntity.ok(res);
    }

    @Operation(
            summary = "Talent 데이터 저장하기 API",
            description = "인재상 데이터를 DB에 저장합니다.",
            tags = "DB 세팅"
    )
    @PostMapping(value = "/talent/save")
    public ResponseEntity<TalentResDTO> saveTalentData(@RequestBody TalentReqDTO talentReqDTO) {
        var data = talentService.save(talentReqDTO);
        var res = TalentResDTO.toResponseDTO(data);
        return ResponseEntity.ok(res);
    }

    @Tag(name = "기업별 인재상 리스트 API")
    @Operation(
            summary = "전체 기업 조회하기 API",
            description = "전체 기업의 데이터를 조회합니다.",
            tags = "기업별 인재상 리스트 API"

    )
    @GetMapping(value = "/company/list")
    public ResponseEntity<List<CompanyResDTO>> getCompanyList() {
        var data = companyService.findAllCompany();

        var res = data.stream()
                .map(CompanyResDTO::toResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(res);
    }

    @Operation(
            summary = "특정 기업 조회하기 API",
            description = "특정 기업의 데이터를 조회합니다.",
            tags = "기업별 인재상 리스트 API"

    )
    @GetMapping(value = "/company/talent/{companyId}")
    public ResponseEntity<List<TalentResDTO>> getTalent(@PathVariable("companyId") Long companyId) {
        var res = talentService.getTalentsByCompanyId(companyId);
        return ResponseEntity.ok(res);
    }
}
