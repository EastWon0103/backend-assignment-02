## 회원 서비스 프로젝트
[PDA 3기] 24.04.08

### 요구사항
- 필수 회원 정보는 id, pw, 닉네임 입니다.
- DB를 대체할 클래스 DAO는 별도로 구현한다.
- class DAO는 필드로 Map을 선언하여 DB 대체로 활용한다. //MemberRepositoryImpl에 구현

### 폴더 구조
```
└── src
    ├── Main.java
    ├── controller
    │   └── Controller.java
    ├── repository
    │   ├── Member.java
    │   ├── MemberRepository.java // 인터페이스
    │   └── MemberRepositoryImpl.java
    ├── service
    │   ├── MemberService.java // 인터페이스
    │   ├── MemberServiceImpl.java
    │   └── dto
    │       ├── LoginResponse.java
    │       └── LogoutResponse.java
    └── util
        └── EncryptionUtil.java
```
### 클래스 다이어그램
![MemberRepository](https://github.com/EastWon0103/shinhan-backend-assignment-02/assets/63653473/965b61aa-6ed6-4681-9d3c-ae8950242b89)
