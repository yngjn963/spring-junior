package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service // 스프링 컨테이너에 서비스로 등록
@Transactional // jpa는 트랜잭션 안에서 실행되어야 한다.
public class MemberService {
    private final MemberRepository memberRepository;

//    @Autowired // 스프링 컨테이너에 있는 리포지토리를 서비스에 주입한다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     *
     * @param member
     * @return
     */
    public Long join(Member member) {
        long start = System.currentTimeMillis();

        try {
            // 같은 이름이 있는 중복 회원은 안된다고 가정하면
            validateDuplicateMember(member); // ctrl + alt + shift + T = 메서드 추출
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> { // 이미 존재하면 -> 람다식
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

            memberRepository.save(member);

            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("join = " + timeMs + "ms");
        }
        /*
        시간 측정 로직 구현을 개별로 했을 때 문제
        - 회원가입, 회원 조회에 시간을 측정하는 기능은 핵심 관심 사항이 아니다.
        - 시간을 측정하는 로직은 공통 관심 사항이다.
        - 시간을 측정하는 로직과 핵심 비즈니스의 로직이 섞여서 유지보수가 어렵다.
        - 시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어렵다.
        - 시간을 측정하는 로직을 변경할 때 모든 로직을 찾아가면서 변경해야 한다.

        AOP
        공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern)
         */
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     *
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 한 명 조회
     *
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
