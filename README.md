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
1. Java 17을 다운로드합니다. (17.0.x 권장)
2. 로컬 환경에서 해당 프로젝트를 git clone 합니다.
   ```
   https://github.com/IntroMe-CareerHub/introme-server.git
   ```
3. gradle 빌드합니다.
   ```
   ./gradlew build
   ```
4. spring boot 서비스를 run합니다.
   ```
   ./gradlew bootrun
   ```
   <img width="608" alt="image" src="https://github.com/IntroMe-CareerHub/introme-server/assets/76420055/54190513-f55b-432d-a5eb-6062dc3b5ae3">
