package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //
@Transactional // 스프링 통합 테스트
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

//    @BeforeEach
//    public void beforeEach() {
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository); // 직접 생성하지 않고 외부에서 리포지토리를 주입받음 = Dependency Injection(DI)
//    }
//
//    @AfterEach
//    public void afterEach() {
//        memberRepository.clearStore();
//    }
    // 메모리에 올라가 있는 데이터를 다음 테스트를 위해 지운 작업 >> 데이터베이스에서 불필요

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