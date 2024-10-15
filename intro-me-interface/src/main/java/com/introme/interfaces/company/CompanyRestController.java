package com.introme.interfaces.company;


import com.introme.company.CompanyService;
import com.introme.company.dto.request.CompanyReqDTO;
import com.introme.company.dto.request.SubmitCompanyReqDTO;
import com.introme.company.dto.response.AllCompaniesResDTO;
import com.introme.company.dto.response.CompanyPageDTO;
import com.introme.company.dto.response.CompanyDetailResDTO;
import com.introme.company.dto.response.SubmitCompanyResDTO;
import com.introme.talent.dto.request.TalentReqDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(title = "기업별 인재상 리스트 API 명세서",
                version = "v1"
        )
)
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CompanyRestController {

    private final CompanyService companyService;

    @Tag(name = "기업별 인재상 리스트 API")

    @Operation(
            summary = "[Admin] 기업 및 인재상 데이터 저장 API",
            description = "관리자가 직접 기업 정보 데이터를 저장합니다.",
            tags = "기업별 인재상 리스트 API"
    )
    @PostMapping(value = "/company/add", produces = "application/json")
    public ResponseEntity<CompanyDetailResDTO> saveCompanyData(@RequestBody CompanyReqDTO companyReqDTO) {
        var company = companyService.save(companyReqDTO);
        var data = CompanyDetailResDTO.toResponseDTO(company);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "[User] 기업 및 인재상 데이터 등록 요청 API",
            description = "사용자가 입력한 기업 정보 데이터를 PENDING 타입으로 저장합니다.",
            tags = "기업별 인재상 리스트 API"
    )
    @PostMapping(value = "/company/submit", produces = "application/json")
    public ResponseEntity<SubmitCompanyResDTO> submitCompanyData(@RequestBody SubmitCompanyReqDTO submitCompanyReqDTO) {
        var company = companyService.submit(submitCompanyReqDTO);
        var data = SubmitCompanyResDTO.toResponseDTO(company);

        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "전체 기업 조회하기 API",
            description = "전체 기업의 데이터를 페이지별로 나누어 조회합니다.",
            tags = "기업별 인재상 리스트 API"
    )
    @GetMapping(value = "/company/list", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    public ResponseEntity<CompanyPageDTO<List<AllCompaniesResDTO>>> getCompanyList(@PageableDefault(size = 12, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        CompanyPageDTO<List<AllCompaniesResDTO>> data = companyService.findAllCompany(pageable);

        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "기업 인재상 조회하기 API",
            description = "특정 기업의 인재상 데이터를 조회합니다.",
            tags = "기업별 인재상 리스트 API"

    )
    @GetMapping(value = "/company/talent/{companyId}")
    public ResponseEntity<CompanyDetailResDTO> getTalent(@PathVariable("companyId") Long companyId) {
        var res = companyService.findCompanyData(companyId);
        return ResponseEntity.ok(res);
    }

    @Operation(
            summary = "기업 검색하기 API",
            description = "특정 기업을 검색합니다.",
            tags = "기업별 인재상 리스트 API"
    )
    @GetMapping(value = "/company/search")
    public ResponseEntity<CompanyPageDTO<List<AllCompaniesResDTO>>> search(@RequestParam String companyName, @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        CompanyPageDTO<List<AllCompaniesResDTO>> data = companyService.findCompanyByCompanyName(companyName, pageable);

        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "[User] 특정 기업 인재상 추가하기 API",
            description = "[User] 특정 기업의 인재상을 추가합니다.",
            tags = "기업별 인재상 리스트 API"
    )
    @PostMapping(value = "/company/talent/submit")
    public ResponseEntity<SubmitCompanyResDTO> addTalents(@RequestParam Long companyId, @RequestBody TalentReqDTO talents) {
        return ResponseEntity.ok(companyService.submitTalent(companyId, talents));
    }


}
