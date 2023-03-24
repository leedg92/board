# board
연습용 개인 게시판 프로젝트


><h3>개발 환경</h3>
>- Intellij IDEA Ultimate <br>
>- Java 17 <br>
>- Gradle 7.6.1 <br>
>- Spring Boot 2.7.0(queryDSL 의존성 추가 방식이 3.0버전 이후로 변경 - 추후 spring boot 버전 변경 예정) <br>

><h3>기술 세부 스택</h3>
> <h4>Spring Boot Actuator</h4>
>- Spring Web <br>
>- Spring Data JPA <br>
>- Rest Repositories <br>
>- Rest Repositories HAL Explorer <br>
>- Thymeleaf <br>
>- Spring Security <br>
>- H2 Database <br>
> MySQL Driver <br>
>- Lombok <br>
>- Spring Boot DevTools <br>
>- Spring Configuration Processor <br>
> <h4>그 외</h4>
> QueryDSL <br>
> Bootstrap<br>
> cloudType (https://port-0-board-6g2llfm48ovu.sel3.cloudtype.app/articles)

<br><br><br>
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
>목표
>- java 기반 웹페이지를 처음부터 빌드해보기
>- SM 업무하면서 사용했던 방식(옛날?)이 아닌 다른 방식으로 개발
>- 아이디어가 생겼을 때 바로 적용할 수 있게끔 새로운 방식에 깊게 적응하기



>checked exception, unchecked exception : https://madplay.github.io/post/java-checked-unchecked-exceptions
>- queryDSL은 checked exception(정적 타입을 이용해 쿼리를 호출함. 자바문법으로 이루어져있어서 컴파일단계에서 예외가 떨어지기때문에 예외처리가 상대적으로 쉽다.)
>  - jpql을 공부해서 두개를 비교해보면 좋을듯



>jwt : https://inpa.tistory.com/entry/WEB-%F0%9F%93%9A-JWTjson-web-token-%EB%9E%80-%F0%9F%92%AF-%EC%A0%95%EB%A6%AC



>참고용

>-equals and hashcode를 overriding한 이유는 엔티티의 모든 필드를 비교할 필요가 없기때문에 id만 비교하기 위해서임(퍼포먼스의 차이).

>-spring data jpa의 쿼리메소드는 도메인단위로 출력을 만들어준다.
> - 스트링형태로 받기 위해 queryDSL을 사용
