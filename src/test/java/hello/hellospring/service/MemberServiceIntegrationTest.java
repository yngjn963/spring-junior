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

@SpringBootTest // 스프링 통합 테스트(스프링 컨테이너와 테스트를 함께 실행 = 스프링을 실제로 실행하여 테스트)
@Transactional // 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후 Rollback: DELETE 쿼리를 작성할 필요가 없다.(DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.)
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
//    @AfterEach
//    public void afterEach() {
//        memberRepository.deleteAll(); // 첫 번째 테스트 이후 두 번째 테스트부터는 DB에서 데이터를 지워야 성공할 수 있다.
//    }

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

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

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