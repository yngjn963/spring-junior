package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
스프링 데이터 JPA
스프링 부트와 JPA만 사용해도 개발 생산성이 정말 많이 증가하고, 개발해야할 코드도 확연히 줄어든다.
여기에 스프링 데이터 JPA를 사용하면, 기존의 한계를 넘어 마치 마법처럼, 리포지토리에 구현 클래스 없이 인터페이스 만으로 개발을 완료할 수 있다.
그리고 반복 개발해온 기본 CRUD 기능도 스프링 데이터 JPA가 모두 제공한다.
 */

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // 첫 번째 인자: Member 객체, 두 번째 인자: PK의 타입. 인터페이스에서 인터페이스를 구현할 때는 extends로 한다.
    @Override
    Optional<Member> findByName(String name);
    // 인터페이스에서 공통으로 사용할 수 없는 비즈니스 로직 구현의 경우
    // 메소드 네이밍 규칙: findBy + 조건(ex: findByName, findByNameAndID)
    // 해당 규칙으로 작성하면 select m from Member m where m.name = ? 쿼리를 작성하는 것과 동일하게 동작한다.

    /*
    참고: 실무에서는 JPA와 스프링 데이터 JPA를 기본으로 사용하고, 복잡한 동적 쿼리는 Querydsl이라는 라이브러리를 사용하면 된다.
    Querydsl을 사용하면 쿼리도 자바 코드로 안전하게 작성할 수 있고, 동적 쿼리도 편리하게 작성할 수 있다.
    이 조합으로 해결하기 어려운 쿼리는 JPA가 제공하는 네이티브 쿼리를 사용하거나, 앞서 학습한 스프링 JdbcTemplate를 사용하면 된다.
     */
}
