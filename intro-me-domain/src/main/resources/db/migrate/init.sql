CREATE TABLE COMPANY (
                         ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                         COMPANY_INFO JSON,
                         IMAGE VARCHAR(255),
                         NAME VARCHAR(255) NOT NULL,
                         PERMISSION VARCHAR(50)
);


CREATE TABLE TALENT (
                        ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                        DESCRIPTION TEXT,
                        KEYWORD VARCHAR(255),
                        COMPANY_ID BIGINT
);