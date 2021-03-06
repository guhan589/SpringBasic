package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repsoitory.MemberRepository;
import hello.hellospring.repsoitory.MemoryMemberRespoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * */
    public Long join(Member member) {

        //같은 이름이 있는 중복 회원x
        /*Optional<Member> byName = memberRepository.findByName(member.getName());
        byName.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/
        validatewDuplicateMember(member); //ctrl + alt + m : Extract Method
        memberRepository.save(member);
        return member.getId();
    }

    private void validatewDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
