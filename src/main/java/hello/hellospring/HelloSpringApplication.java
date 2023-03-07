package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
		/*
		톰캣 웹서버 내장
		 */

		/*
		라이브러리 살펴보기
		메이븐이나 그래들 같은 빌드 도구는 의존 관계의 라이브러리를 관리한다.
		예를 들어 spring-boot-starter-web 라이브러리를 가져오면, spring-boot-starter-web이 필요로 하는 라이브러리를 모두 가져온다.
		spring-boot-starter-web 라이브러리는 의존 관계로 spring-boot-starter-tomcat 라이브러리를 내장하고 있어 별도로 웹 서버를 설치하지 않아도 서버를 실행할 수 있다.
		이처럼 내장된 웹 서버를 임베디드(embedded)라고 부른다.

		logging 라이브러리
		slf4j: 인터페이스 개념
		logback: 구현체 개념

		test 라이브러리
		junit: 테스트 프레임워크
		mockito: 목 라이브러리
		assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
		spring-test: 스프링 통합 테스트 지원
	 */
	}

}
