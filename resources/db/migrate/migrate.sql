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
       ('신한은행','{ "location": "서울특별시 강서구", "url": "www.kakaocorp.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'shinhan.png', '#EEEEEE'),
       ('네이버','{ "location": "경기도 성남시 분당구 분당내곡로 131", "url": "https://www.navercorp.com/", "techBlog": "https://d2.naver.com/home", "recruitUrl":"https://recruit.navercorp.com/" }' ,'naver.png', '#00FF7F'),
       ('대구신용보증재단','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'daegu.jpg', '#EEEEEE'),
       ('당근','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'dangeun.png', '#FF8000'),
       ('하나은행','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'hanabank.jpeg', '#EEEEEE'),
       ('국민은행','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'kbbank.png', '#FFBF00'),
       ('국민카드','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'kbcard.jpeg', '#FFBF00'),
       ('KT','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'kt.png', '#2ECCFA'),
       ('농협은행','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'nonghyub.jpg', '#EEEEEE'),
       ('삼성','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'samsung.jpg', '#0101DF'),
       ('스타벅스','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'starbucks.jpg', '#088A29'),
       ('테크라','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'tekra.jpg', '#EEEEEE'),
       ('토스','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'toss.jpg', '#EEEEEE'),
       ('카카오뱅크','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'kakaobank.png', '#FFFF00'),
       ('맥도날드','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'macdo.jpg', '#EEEEEE'),
       ('SK','{ "location": "서울특별시 강서구", "url": "www.naver.com", "techBlog": "www.techblog.com", "recruitUrl":"www.recruit.com" }' ,'sk.jpeg', '#EEEEEE');

INSERT INTO Talent (keyword, description, company_id, icon, base_url)
VALUES ('고객중심', '고객이 원하는 최고의 경험을 만든다.', 1, '😃', 'baseurl'),
       ('주도적 몰입', '자율과 책임에 기반하여 성과를 낸다.', 2, '👩🏻‍💻', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 3, '💪🏻', 'baseurl'),
       ('유기적인 협업', '공동의 목표를 달성합니다.', 2, '👯', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 4, '💪🏻', 'baseurl'),
       ('발전적 피드백', '피드백을 통해 같이 성장합니다.', 5, '🗣️', 'baseurl'),
       ('발전적 피드백', '피드백을 통해 같이 성장합니다.', 6, '🗣️', 'baseurl'),
       ('대담한 도전', '현재에 만족하지 않고 도전한다.', 7, '💪🏻', 'baseurl'),
       ('발전적 피드백', '피드백을 통해 같이 성장합니다.', 8, '🗣️', 'baseurl'),
       ('빠른 실행', '문제의 본질에 집중하고 빠르게 실행합니다.', 9, '♻️', 'baseurl'),
       ('자유로운 토론', '더 나은 결과를 위해 토론합니다.', 10, '🎙️', 'baseurl'),
       ('빠른 실행', '문제의 본질에 집중하고 빠르게 실행합니다.', 11, '♻️', 'baseurl'),
       ('혁신', '가보지 않은 길을 두려워하지 않습니다.', 10, '💡', 'baseurl'),
       ('주도성', '스스로 몰입하고 주도적으로 일합니다.', 11, '🙋🏻', 'baseurl'),
       ('혁신', '가보지 않은 길을 두려워하지 않습니다.', 7, '💡', 'baseurl'),
       ('주도성', '스스로 몰입하고 주도적으로 일합니다.', 17, '🙋🏻', 'baseurl'),
       ('주도성', '스스로 몰입하고 주도적으로 일합니다.', 12, '🙋🏻', 'baseurl'),
       ('사회적 책임감', '보다 나은 세상을 만들기 위해 노력합니다.', 12, '🌍', 'baseurl'),
       ('사회적 책임감', '보다 나은 세상을 만들기 위해 노력합니다.', 13, '🌍', 'baseurl'),
       ('차별성', '대담한 목표를 가지고 핵심에 집중하여 차별성을 가집니다.', 14, '🌟', 'baseurl'),
       ('차별성', '대담한 목표를 가지고 핵심에 집중하여 차별성을 가집니다.', 15, '🌟', 'baseurl'),
       ('차별성', '대담한 목표를 가지고 핵심에 집중하여 차별성을 가집니다.', 16, '🌟', 'baseurl'),
       ('사회적 책임감', '보다 나은 세상을 만들기 위해 노력합니다.', 17, '🌍', 'baseurl');