package hello.hellospring.repository;

/*
회원 리포지토리 테스트 케이스 작성
개발한 기능을 실행해서 테스트 할 때 자바의 main 메서드를 통해서 실행하거나, 웹 애플리케이션의 컨트롤러를 통해서 해당 기능을 실행한다.
이러한 방법은 준비하고 실행하는데 오래 걸리고, 반복 실행하기 어렵고 여러 테스트를 한 번에 실행하기 어렵다는 단점이 있다.
자바는 JUnit이라는 프레임워크로 테스트를 실행해서 이러한 문제를 해결한다.
 */

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(member, result); // 기대값, 실제값
        assertThat(member).isEqualTo(result); // shift + Enter = static으로 import
    }

    @Test
    public void findByName() {
        Member member1 = new Member();

        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();

        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
}
