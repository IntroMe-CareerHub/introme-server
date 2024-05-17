CREATE TABLE COMPANY (
                         ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                         COMPANY_INFO JSON,
                         IMAGE VARCHAR(255),
                         NAME VARCHAR(255) NOT NULL,
                         PERMISSION VARCHAR(50) DEFAULT 'PENDING'
)
    ENGINE=InnoDB
	default charset=utf8 collate utf8_general_ci;
;

CREATE TABLE TALENT (
                        ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                        DESCRIPTION TEXT,
                        KEYWORD VARCHAR(255),
                        COMPANY_ID BIGINT
)
    ENGINE=InnoDB
	default charset=utf8 collate utf8_general_ci;
;

INSERT INTO Company (name, company_info ,image)
VALUES ('카카오', '{ "address": "서울특별시 강서구", "website": "www.kakaocorp.com", "phone": "010-0000-00000" }' , '.icon'),
       ('신한은행','{ "address": "서울특별시 강서구", "website": "www.kakaocorp.com", "phone": "010-0000-00000" }' ,'.icon'),
       ('네이버','{ "address": "서울특별시 강서구", "website": "www.naver.com", "phone": "010-0000-00000" }' ,'.icon');

INSERT INTO Talent (keyword, description, company_id)
VALUES ('고객중심', '고객이 원하는 최고의 경험을 만든다.', 1),
       ('주도적 몰입', '자율과 책임에 기반하여 성과를 낸다.', 2),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 3),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 2);