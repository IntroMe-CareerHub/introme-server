CREATE TABLE COMPANY (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         company_info JSON,
                         IDENTITY_COLOR VARCHAR(255) NOT NULL,
                         IMAGE VARCHAR(255) NOT NULL,
                         NAME VARCHAR(255) NOT NULL,
                         PERMISSION VARCHAR(50) DEFAULT 'APPROVED',
                         updated_at datetime not null default CURRENT_TIMESTAMP
)
    ENGINE=InnoDB
    default charset=utf8mb4 collate utf8mb4_general_ci;


CREATE TABLE TALENT (
                        ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                        DESCRIPTION TEXT,
                        KEYWORD VARCHAR(255) NOT NULL,
                        ICON VARCHAR(255),
                        BASE_URL VARCHAR(255),
                        PERMISSION VARCHAR(50) DEFAULT 'APPROVED',
                        COMPANY_ID BIGINT
)
    ENGINE=InnoDB
    default charset=utf8mb4 collate utf8mb4_general_ci;

CREATE TABLE USER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(64) NOT NULL,
    name VARCHAR(64) NOT NULL,
    role VARCHAR(16) NOT NULL,
    picture VARCHAR(512),
    provider_account_id VARCHAR(512) NULL,
    provider_type VARCHAR(32) NULL
) ENGINE=InnoDB
 default charset=utf8mb4 collate utf8mb4_general_ci;

INSERT INTO Company (name, company_info ,image, identity_color)
VALUES ('카카오', '{ "location": "경기 성남시 분당구 판교역로 221", "url": "www.kakaocorp.com", "techBlog": "https://tech.kakao.com/blog", "recruitUrl":"https://careers.kakao.com/index" }' , 'kakao.png', '#FFFF00'),
       ('신한은행','{ "location": "서울특별시 강서구", "url": "www.kakaocorp.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'.icon', '#EEEEEE'),
       ('네이버','{ "location": "경기도 성남시 분당구 분당내곡로 131", "url": "https://www.navercorp.com/, "techBlog": "https://d2.naver.com/home", "recruitUrl":"https://recruit.navercorp.com/" }' ,'naver.png', '#00FF7F'),
       ('네이버2','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'.icon', '#EEEEEE'),
       ('네이버3','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'.icon', '#EEEEEE'),
       ('네이버4','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'.icon', '#EEEEEE'),
       ('네이버5','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'.icon', '#EEEEEE'),
       ('네이버7','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'.icon', '#EEEEEE'),
       ('네이버8','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'.iconㅇ', '#EEEEEE'),
       ('네이버9','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'.icon', '#EEEEEE'),
       ('네이버1212','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'.icon', '#EEEEEE'),
       ('네이버234','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'.icon', '#EEEEEE'),
       ('네이버222','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'.icon', '#EEEEEE');

INSERT INTO Talent (keyword, description, company_id, icon, base_url)
VALUES ('고객중심', '고객이 원하는 최고의 경험을 만든다.', 1, '.Talent_icon', 'baseurl'),
       ('주도적 몰입', '자율과 책임에 기반하여 성과를 낸다.', 2, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 3, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 2, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 4, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 5, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 6, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 7, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 8, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 9, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 10, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 11, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 10, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 11, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 7, '.Talent_icon', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 7, '.Talent_icon', 'baseurl');