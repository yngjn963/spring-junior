package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // Optional이란? 제네릭 객체가 null일 때, 그대로 반환하지 않고 Optional로 감쌀 때 사용

}
