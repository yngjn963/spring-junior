package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em; // build.gradle에 jpa dependencies를 등록하면(라이브러리를 받으면) 스프링 부트가 EntityManager를 자동으로 생성해 준다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);

        return member;
        /*
        jpa가 insert 쿼리를 만들고, id까지 member 객체에 set해 준다.
         */
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);

        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();

        return result.stream().findAny();
    }
    // 단건이 아닌(PK 조건이 아닌) List를 결과물로 받을 때에는 JPQL 쿼리를 작성해줘야 한다.

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList(); // Member 엔티티를 조회. select m: 엔티티 m 자체를 조회.
    }
}
