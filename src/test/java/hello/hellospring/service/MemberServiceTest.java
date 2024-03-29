package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // MemberService 객체에 MemoryMemberRepository 필드가 있기 때문에 다른 인스턴스를 생성하는 것은 개념적으로 옳지 않다.
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // 직접 생성하지 않고 외부에서 리포지토리를 주입받음 = Dependency Injection(DI)
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. 123");
//        }

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 정상적으로 오류가 발생하는지 확인
//        assertThrows(NullPointerException.class, () -> memberService.join(member2)); // 정상적으로 오류가 발생하는지 확인

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}