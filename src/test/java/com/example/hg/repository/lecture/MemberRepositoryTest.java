package com.example.hg.repository.lecture;

import com.example.hg.model.lecture.Member;
import com.example.hg.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

// @RunWith(SpringRunner.class)  // junit5 부터는 사용하지 않음
@SpringBootTest
@Transactional
@Rollback(false) // 로컬 테스트 시 데이터 입력 확인
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testSave() {
        Member member = new Member("testUsername");
        Member findMember = memberRepository.save(member);

        // Assertions.assertThat() .. . -> import static ...
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        // 동일 tx에서는 영속성 컨텍스트 동일성 보장 (1차 캐시. 다른 말로 tx 다르면 다른 객체)
        assertThat(findMember).isEqualTo(member);
    }

}