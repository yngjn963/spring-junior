package hello.hellospring.repository;

import hello.hellospring.domain.Member;

public interface MemberRepository {
    Member save(Member member);
}
