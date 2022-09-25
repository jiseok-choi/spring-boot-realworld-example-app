# RealWorld Server by Spring boot



## 추가 작업사항

1. article 추가 수정 삭제 관련한 history 기록하는 기능을 추가하였습니다.

2. history 조회 할 수 있는 API 를 추가하였습니다. 
   
   - 로그인 사용자는 본인이 작업한 article 관련 history 만 조회 가능합니다.

3. history 조회시 pagenation 기능이 구현되어 있습니다.



## 변경사항

- Spring Data JPA 를 사용하여 history 작업을 진행합니다.

- history_article 테이블을 추가하였습니다. 

- history 작업에 대한 Datasource 를 분리했습니다. (DB 분리)

- postgresql 쿼리에 맞게 기존 query (mybatis) 를 수정하였습니다.
