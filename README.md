### Project Structure
```
├─ intro-me-domain
│  └─ src
│     ├─ main
│     │  └─ java
│     │     └─ com
│     │        └─ introme
│     │           ├─ company
│     │           │  ├─ CompanyInfoJsonConverter.java
│     │           │  ├─ CompanyService.java
│     │           │  ├─ dto
│     │           │  │  ├─ request
│     │           │  │  │  ├─ CompanyReqDTO.java
│     │           │  │  │  └─ SubmitCompanyReqDTO.java
│     │           │  │  └─ response
│     │           │  │     ├─ AllCompaniesResDTO.java
│     │           │  │     ├─ CompanyDetailResDTO.java
│     │           │  │     ├─ CompanyPageDTO.java
│     │           │  │     └─ SubmitCompanyResDTO.java
│     │           │  ├─ entity
│     │           │  │  ├─ Company.java
│     │           │  │  ├─ CompanyInfo.java
│     │           │  │  ├─ PageInfo.java
│     │           │  │  └─ Permission.java
│     │           │  └─ repository
│     │           │     └─ CompanyRepository.java
│     │           ├─ exception
│     │           │  ├─ CommonErrorCode.java
│     │           │  ├─ CustomException.java
│     │           │  ├─ ErrorCode.java
│     │           │  ├─ ErrorResponse.java
│     │           │  ├─ GlobalExceptionHandler.java
│     │           │  └─ ServiceErrorCode.java
│     │           └─ talent
│     │              ├─ dto
│     │              │  ├─ request
│     │              │  │  └─ TalentReqDTO.java
│     │              │  └─ response
│     │              │     └─ TalentResDTO.java
│     │              ├─ entity
│     │              │  └─ Talent.java
│     │              └─ repository
│     │                 └─ TalentRepository.java
├─ intro-me-interface
│  └─ src
│     ├─ main
│     │  └─ java
│     │     └─ com
│     │        └─ introme
│     │           ├─ IntroMeInterfaceApplication.java
│     │           └─ company
│     │              └─ CompanyRestController.java
```

<br>

### Project Setting
1. Docker를 설치합니다.
2. 로컬 환경에서 해당 프로젝트를 git clone 합니다.
   ```
   https://github.com/IntroMe-CareerHub/introme-server.git
   ```
3. 전달드린 설정파일들은 관련 위치에 세팅합니다.
4. Docker 컨테이너를 띄웁니다.
   ```bash
   docker-compose up -d
   ```
   컨테이너 삭제 방법
   ```
   docker-compose down -v
   ```
