package com.introme.intromeinterface;

import com.introme.company.entity.Company;
import com.introme.company.entity.CompanyInfo;
import com.introme.company.entity.Permission;
import com.introme.company.repository.CompanyRepository;
import com.introme.talent.entity.Talent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class IntroMeInterfaceApplicationTests {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void insertCompanies() {
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
    public void paging() {
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
}

