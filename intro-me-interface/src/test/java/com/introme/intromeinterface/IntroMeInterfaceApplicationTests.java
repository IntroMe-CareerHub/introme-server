package com.introme.intromeinterface;

import com.introme.company.CompanyService;
import com.introme.company.entity.Company;
import com.introme.company.entity.CompanyInfo;
import com.introme.company.entity.Permission;
import com.introme.company.repository.CompanyRepository;
import com.introme.talent.entity.Talent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
class IntroMeInterfaceApplicationTests {

    @Autowired
    private CompanyRepository companyRepository;
    private CompanyService companyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("companyRepository에 데이터 저장")
    public void insertCompanies() throws Exception {
        for (int i = 0; i < 20; i++) {
            Talent talent = Talent.builder()
                    .keyword("인재" + i)
                    .description("자세한 내용")
                    .icon(".icon")
                    .permission(Permission.APPROVED)
                    .baseUrl("")
                    .build();
            List<Talent> talentList = new ArrayList<>();
            talentList.add(talent);

            CompanyInfo companyInfo = new CompanyInfo("위치", "url", "채용 사이트", "");

            Company company = Company.builder()
                    .name("company" + i)
                    .image(".png")
                    .identityColor("#EEEEEE")
                    .companyInfo(companyInfo)
                    .permission(Permission.APPROVED)
                    .talents(talentList)
                    .build();

            companyRepository.save(company);
        }
    }

    @Test
    @DisplayName("Pagination 결과 확인")
    public void paging() throws Exception {
        // Given
        Pageable pageable = PageRequest.of(0, 12);

        // When & Then
        Page<Company> page = companyRepository.findAllByPermission(Permission.APPROVED, pageable);

        assertThat(page.getTotalElements()).isEqualTo(13);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(12);
        assertThat(page.hasNext()).isTrue();
    }

    @Test
    @DisplayName("[User] 기업 및 인재상 데이터 등록 요청 API 테스트")
    public void submitDataByUser() throws Exception {

    }

    @Test
    @DisplayName("기업 검색하기 API 테스트")
    public void findOneCompany() throws Exception {
        mockMvc.perform(
                        get("/api/v1/company/search")
                                .param("keyword", "은행")
                                .param("page", "0")
                                .param("size", "10")
                                .param("sort", "id")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("신한은행"))
                .andExpect(jsonPath("$.data[0].talents[0].keyword").value("주도적 몰입"))
                .andExpect(jsonPath("$.data[0].talents[1].keyword").value("대담한 도전"))
                .andExpect(jsonPath("$.pageInfo.page").value(1))
                .andExpect(jsonPath("$.pageInfo.size").value(10))
                .andExpect(jsonPath("$.pageInfo.totalElements").value(2))
                .andExpect(jsonPath("$.pageInfo.totalPages").value(1));
    }
}

