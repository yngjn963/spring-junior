package hello.hellospring.domain;

/*
컨트롤러: 웹 MVC의 컨트롤러 역할
서비스: 핵심 비즈니스 로직 구현
리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
도메인: 비즈니스 도메인 객체. 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨

클래스 의존관계
MemberService ---------- MemberRepository(Interface)
                                    |
                           MemoryMemberRepository
아직 데이터 저장소가 선정되지 않았다는 가정하에, 우선 인터페이스로 구현 클래스를 변경할 수 있도록 설계
개발을 진행하기 위해 초기 개발 단계에서는 구현체로 가벼운 메모리 기반의 데이터 저장소 사용
 */

public class Member {
}
