package com.introme.company;


import com.introme.company.dto.request.CompanyReqDTO;
import com.introme.company.dto.response.CompanyResDTO;
import com.introme.company.dto.response.TempCompanyResDTO;
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

    @Tag(name = "DB 세팅")
    @Operation(
            summary = "[Admin] 기업 인재상 데이터 저장 API",
            description = "검토한 기업 정보 데이터를 DB에 저장합니다.",
            tags = "DB 세팅"
    )
    @PostMapping(value = "/company/add", produces = "application/json")
    public ResponseEntity<CompanyResDTO> saveCompanyData(@RequestBody CompanyReqDTO companyReqDTO) {
        var data = companyService.save(companyReqDTO);
        var res = CompanyResDTO.toResponseDTO(data);
        return ResponseEntity.ok(res);
    }

    @Operation(
            summary = "[User] 기업 인재상 데이터 등록 요청 API",
            description = "사용자가 입력한 기업 정보 데이터를 임시 DB 테이블에 저장합니다.",
            tags = "DB 세팅"
    )
    @PostMapping(value = "/company/submit", produces = "application/json")
    public ResponseEntity<TempCompanyResDTO> submitCompanyData(@RequestBody CompanyReqDTO companyReqDTO) {
        var data = companyService.submit(companyReqDTO);
        var res = TempCompanyResDTO.toResponseDTO(data);
        System.out.println(res);
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
            summary = "기업 인재상 조회하기 API",
            description = "특정 기업의 인재상 데이터를 조회합니다.",
            tags = "기업별 인재상 리스트 API"

    )
    @GetMapping(value = "/company/talent/{companyId}")
    public ResponseEntity<CompanyResDTO> getTalent(@PathVariable("companyId") Long companyId) {
        var res = companyService.findCompanyData(companyId);
        return ResponseEntity.ok(res);
    }

    @Operation(
            summary = "기업 검색하기 API",
            description = "특정 기업을 검색합니다.",
            tags = "기업별 인재상 리스트 API"
    )
    @GetMapping(value = "/company/search")
    public ResponseEntity<List<CompanyResDTO>> search(@RequestParam String keyword) {
        var res = companyService.findCompanyByKeyword(keyword);
        return ResponseEntity.ok(res);
    }

}
