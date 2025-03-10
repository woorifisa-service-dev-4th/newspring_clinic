## NewSpring_Clinic
<br> 

## 👨‍💻 팀원 소개
| 김새봄 | 마서영 | 윤선영 |
| --- | --- | --- |
| <img src="https://avatars.githubusercontent.com/newspring0203" > | <img src="https://avatars.githubusercontent.com/luxihua" > | <img src="https://avatars.githubusercontent.com/yunsy1103"> | 
| Owner 검색기능 구현 | Owner 추가,수정기능 구현 | Pet 추가기능 구현 |

<br>

## ✏️ 기능 소개 
**Spring MVC의 Controller-Service-View 패턴 적용**
<br>
**Spring Boot + JPA를 활용하여 Owner, Pet, Veterinarian, Visit 데이터를 관리**
<br><br>
**1. Owner 추가, 수정 기능**
  - GetMapping
    * 검색 기능 & 전체 목록 조회
    * Owner 검색/추가/수정 폼을 보여주는 GET 요청
    * 특정 Owner 상세 정보 조회 
  - PostMapping
    * Owner 저장 
    * Owner 수정 저장
<br>

**2. Pet 추가, 수정 기능**
  - GetMapping
    * "Add new Pet" 클릭 시 새로운 Pet 추가 폼을 받아오는 GET 요청
    * "Edit Pet" 클릭 시 pet 수정 폼을 전달하는 GET 요청
  - PostMapping
    * "Add Pet" 클릭 시 새로운 pet을 저장하는 POST 요청
    * "Edit Pet" 클릭 시 수정된 pet을 저장하는 POST 요청
<br>

**3. Veterinarians 조회 기능**
  - GetMapping을 사용한 목록 조회
<br>

**4. Visit 추가 기능**
   - GetMapping
     * "Add Visit" 클릭 시 Visit 추가 폼을 받아오는 GET 요청
   - PostMapping
     * Visit 저장
<br><br>
