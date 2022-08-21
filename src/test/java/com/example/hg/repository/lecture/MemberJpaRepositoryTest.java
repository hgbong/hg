package com.example.hg.repository.lecture;

import com.example.hg.model.lecture.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// @RunWith(SpringRunner.class)  // junit5 부터는 사용하지 않음
@SpringBootTest
@Transactional
// @Rollback(false) // 로컬 테스트 시 데이터 입력 확인
class MemberJpaRepositoryTest {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    public void testSave() {
        Member member = new Member("testUsername");
        Member findMember = memberJpaRepository.save(member);

        // Assertions.assertThat() .. . -> import static ...
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        // 동일 tx에서는 영속성 컨텍스트 동일성 보장 (1차 캐시. 다른 말로 tx 다르면 다른 객체)
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void testFindByUsernameAndAgeGreaterThan() {

        Member m1 = new Member("name1", 20, null);
        Member m2 = new Member("name2", 25, null);
        Member m3 = new Member("name3", 30, null);

        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);
        memberJpaRepository.save(m3);


        List<Member> result = memberJpaRepository.findByUsernameAndAgeGreaterThan("name2", 20);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getUsername()).isEqualTo("name2");

    }

    @Test
    public void testFindByNamedQuery() {
        List<Member> testusername = memberJpaRepository.findByUsernameUsingNamedQuery("testusername");
    }
}