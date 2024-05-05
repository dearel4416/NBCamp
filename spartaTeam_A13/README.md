# spartaTeam_A13 (사타리 타조)🪜
## sns 페이지 만들기

- 필수 구현 기능
    - [ ]  **사용자 인증 기능**
        - 회원가입 기능
            - username, password를 Client에서 전달받기
            - username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
            - password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자`로 구성되어야 한다.
            - DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기
            - 회원 권한 부여하기 (ADMIN, USER) - ADMIN 회원은 모든 게시글, 댓글 수정 / 삭제 가능
            - 참고자료
                1. https://mangkyu.tistory.com/174
                2. [https://ko.wikipedia.org/wiki/정규_표현식](https://ko.wikipedia.org/wiki/%EC%A0%95%EA%B7%9C_%ED%91%9C%ED%98%84%EC%8B%9D)
                3. https://bamdule.tistory.com/35
                
        - 로그인 및 로그아웃 기능
            - username, password를 Client에서 전달받기
            - DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
            - 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고, 
            발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기
    - [ ]  **프로필 관리**
        - 프로필 수정 기능
            - 이름, 한 줄 소개와 같은 기본적인 정보를 볼 수 있어야 하며 수정할 수 있어야 합니다.
            - 비밀번호 수정 시에는 비밀번호를 한 번 더 입력받는 과정이 필요합니다.
            - 최근 3번안에 사용한 비밀번호는 사용할 수 없도록 제한합니다.
    - [ ]  **게시물 CRUD 기능**
        - 게시물 작성, 조회, 수정, 삭제 기능
            - 게시물 조회를 제외한 나머지 기능들은 전부 인가(Authorization) 개념이 적용되어야 하며 이는 JWT와 같은 토큰으로 검증이 되어야 할 것입니다.
            - 예컨대, 내가 작성한 글을 남이 수정하거나 삭제할 수는 없어야 하고 오로지 본인만 수정/삭제 할 수 있어야겠죠?
            - 전체 게시글 정보를 조회하는 기능도 필요합니다.
    - [ ]  **댓글 CRUD 기능**
        - 댓글 작성, 조회, 수정, 삭제 기능
            - 사용자는 게시물에 댓글을 작성할 수 있고 본인의 댓글은 수정 및 삭제를 할 수 있어야 합니다.
            - 또한, 게시물과 마찬가지로 댓글 조회를 제외한 나머지 기능들은 인가(Authorization)개념이 적용되어야 합니다.
- 추가 구현 기능
    - [ ]  **소셜 로그인 기능 구현**
    - [ ]  **백오피스 만들어보기**
    - [ ]  **좋아요 기능**
    - [ ]  **팔로우 기능 구현**


## [Figma](https://www.figma.com/file/B8V42nuYada7mxXVkpRkTT/%EC%82%AC%EB%8B%A4%EB%A6%AC%EC%A1%B0?type=design&node-id=0-1&mode=design&t=7nI7N57Cg9nSY0cP-0)
![image](https://github.com/de123456sdf/spartaTeam_A13/assets/134623719/d42e9569-6b76-4281-80cf-04e8501d0222)
## [ERD](https://www.erdcloud.com/d/9sNKqNHEJPGqxNEkJ)
![image](https://github.com/de123456sdf/spartaTeam_A13/assets/134623719/c85fa2c7-210d-4ce0-94bb-23e4990470fe)

## [시연영상](https://www.youtube.com/watch?v=NpkRNHXMPVw)
![image](https://github.com/de123456sdf/spartaTeam_A13/assets/134623719/3aa7bfd7-dfa1-4476-ba97-868ef16ccad7)



