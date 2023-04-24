package hello.hellospring.web;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // Spring 실행 시 Controller 객체를 들고 있는다 = 스프링 컨테이너에서 스프링 빈을 관리한다.
public class MemberController {
    private final MemberService memberService; // 초기화 하지 않고 하나의 인스턴스를 공용으로 사용한다.

    @Autowired // 스프링 컨테이너에서 Controller와 MemberService를 연결한다. = DI
    public MemberController(MemberService memberService) { // 오류 발생: memberService가 스프링 빈으로 등록되어 있지 않다.
        this.memberService = memberService;
    }

    /*
    스프링 빈을 등록하는 2가지 방법
    - 컴포넌트 스캔과 자동 의존관계 설정
    - 자바 코드로 직접 스프링 빈 등록하기
     */
}
