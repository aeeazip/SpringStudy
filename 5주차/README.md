## 스프링 부트
- IntellJ 스프링 부트 초기 설정
- 스프링 부트에서 JUnit4 테스트 코드 작성
- Domain과 Domain 테스트 코드 작성 (Entity, Repository)

### IntelliJ 스프링 부트 초기 설정
- gradle 프로젝트 생성
- build.gradle을 스프링 부트 프로젝트로 변경하는 방법

### 스프링 부트에서 JUnit4 테스트 코드 작성
- HelloControllerTest로 Controller에 대한 테스트 코드 작성
- HelloResponseDTO로 entity > 생성자에 대한 테스트 코드 작성
- Lombok 설치 및 적용

### Domain과 Domain 테스트 코드 작성
- Entity와 Repository 같은 패키지에 위치
- Entity 각종 Annotation 주석으로 정리
- Jpa에서는 Repository가 Dao 역할

### Controller / Service / Repository / Dto와 테스트 코드 작성
- 호출 순서 : Controller > Service > Repository
- 특정 계층에서 역할과 Annotation 주석으로 정리
- ControllerTest에서 @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 와 TestRestTemplate 사용 -> JPA 기능 테스트 
