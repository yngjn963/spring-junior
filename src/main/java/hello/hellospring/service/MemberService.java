package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     *
     * @param member
     * @return
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원은 안된다고 가정하면
        validateDuplicateMember(member); // ctrl + alt + shift + T = 메서드 추출
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> { // 이미 존재하면 -> 람다식
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers() {

    }
}
