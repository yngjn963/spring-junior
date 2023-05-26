package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // Spring 실행 시 Controller 객체를 들고 있는다 = 스프링 컨테이너에서 스프링 빈을 관리한다.
public class MemberController {
    private final MemberService memberService; // 초기화 하지 않고 하나의 인스턴스를 공용으로 사용한다.

    @Autowired // 스프링 컨테이너에서 Controller와 MemberService를 연결한다. = DI
    public MemberController(MemberService memberService) { // 오류 발생: memberService가 스프링 빈으로 등록되어 있지 않다.
        this.memberService = memberService;

        System.out.println("memberService = " + memberService.getClass());
        // memberService = class hello.hellospring.service.MemberService$$EnhancerBySpringCGLIB$$de89008: MemberService를 복제해서 코드를 조작
    }

    /*
    스프링 빈을 등록하는 2가지 방법
    - 컴포넌트 스캔과 자동 의존관계 설정: @Controller, @Service, @Repository 안에 @Component = 스프링 컨테이너 안에 객체가 등록되어, Controller - Service - Repository 관계를 사용
    - 자바 코드로 직접 스프링 빈 등록하기
     */
    /*
    아무 클래스에 어노테이션을 붙여도 스프링 빈으로 사용 가능?
    불가능
    = hello.hellospring.HelloSpringApplication에서 스프링이 동작
    하위 패키지에 탐색하기 때문에 하위 패키지가 아니면 사용 불가능하다.
    (설정 시에는 사용 가능(@ComponentScan), 기본적으로 불가능)
     */
    /*
    스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다(Controller, Service, Repository).
    따라서 같은 스프링 빈이면 모두 같은 인스턴스다.
    설정으로 싱글톤이 아니게 설정할 수 있지만, 특별한 경우를 제외하면 대부분 싱글톤을 사용한다.
     */
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

//        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/"; // 회원가입이 끝나면 home 화면으로 redirect
    }

    @GetMapping
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }

}
