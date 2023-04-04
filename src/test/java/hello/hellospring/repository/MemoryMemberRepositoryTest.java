package hello.hellospring.repository;

/*
회원 리포지토리 테스트 케이스 작성
개발한 기능을 실행해서 테스트 할 때 자바의 main 메서드를 통해서 실행하거나, 웹 애플리케이션의 컨트롤러를 통해서 해당 기능을 실행한다.
이러한 방법은 준비하고 실행하는데 오래 걸리고, 반복 실행하기 어렵고 여러 테스트를 한 번에 실행하기 어렵다는 단점이 있다.
자바는 JUnit이라는 프레임워크로 테스트를 실행해서 이러한 문제를 해결한다.
 */

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 개별 테스트 종료 후 실행
    public void afterEach() {
        repository.clearStore(); // 개별 테스트 종료 후 저장소를 비워 다음 테스트를 준비한다(의존 관계에 있는 테스트끼리 영향을 받지 않도록 한다).
    }

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

    @Test
    public void findAll() {
        Member member1 = new Member();

        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();

        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
    // 테스트는 순서에 의존적이면 안된다.
    // 테스트 순서는 소스 순서대로 동작하는 것이 아니기 때문에 다른 테스트에 영향을 받지 않도록 해야 한다.

    /*
    학습 시에는 클래스를 만들고 테스트하였지만,
    반대로 어떠한 목적의 테스트(틀)를 만들고 이 후 구성한 클래스가 테스트에 맞는지 확인하는 방법이 있다.
    이를 테스트 주도 개발(TDD)이라 한다.
     */
}
