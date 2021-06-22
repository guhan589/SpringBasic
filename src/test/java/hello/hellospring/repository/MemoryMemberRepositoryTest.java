package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repsoitory.MemberRepository;
import hello.hellospring.repsoitory.MemoryMemberRespoitory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {
    MemoryMemberRespoitory repository = new MemoryMemberRespoitory();

    @AfterEach

    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();

//        Assertions.assertEquals(member, result);
        Assertions.assertThat(member).isEqualTo(result);

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

        Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> all = repository.findAll();

        Assertions.assertThat(all.size()).isEqualTo(2);
    }
}
