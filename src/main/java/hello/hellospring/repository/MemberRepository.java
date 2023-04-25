package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // Optional이란? 제네릭 객체가 null일 때, 그대로 반환하지 않고 Optional로 감쌀 때 사용
    List<Member> findAll();

}
