package com.introme;

import com.introme.company.entity.Company;
import com.introme.company.entity.CompanyInfo;
import com.introme.talent.entity.Talent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataRunner implements ApplicationRunner {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        CompanyInfo companyInfo = new CompanyInfo("서울특별시 강서구", "www.naver.com", "010-0000-0000");
        Company company = new Company("카카오", ".icon", companyInfo);
        Talent talent = new Talent("고객중심", "고객이 원하는 최고의 경험을 만든다.", company);

        CompanyInfo companyInfo2 = new CompanyInfo("서울특별시 강남구", "www.naver.com", "010-0000-0000");
        Company company2 = new Company("신한은행", ".icon", companyInfo2);
        Talent talent2 = new Talent("주도적 몰입", "자율과 책임에 기반하여 성과를 낸다.", company2);

        CompanyInfo companyInfo3 = new CompanyInfo("서울특별시 강남구", "www.naver.com", "010-0000-0000");
        Company company3 = new Company("네이버", ".icon", companyInfo3);
        Talent talent3 = new Talent("대담한 도전", "현재에 만족하지 않고 도전한다.", company3);

        Talent talent4 = new Talent("대담한 도전", "현재에 만족하지 않고 도전한다.", company2);

        em.persist(company);
        em.persist(talent);

        em.persist(company2);
        em.persist(talent2);

        em.persist(company3);
        em.persist(talent3);
        em.persist(talent4);
    }
}
