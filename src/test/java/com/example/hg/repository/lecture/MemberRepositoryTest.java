package com.example.hg.repository.lecture;

import com.example.hg.model.lecture.Member;
import com.example.hg.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// @RunWith(SpringRunner.class)  // junit5 부터는 사용하지 않음
@SpringBootTest
@Transactional
@Rollback(false)
    // 로컬 테스트 시 데이터 입력 확인
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


    @Test
    public void testFindByUsernameAndAgeGreaterThan() {

        Member m1 = new Member("name111", 20, null);
        Member m2 = new Member("name222", 25, null);
        Member m3 = new Member("name333", 30, null);
        Member m4 = new Member("name111", 30, null);
        Member m5 = new Member("%me1", 30, null);
        Member m6 = new Member("%me1%", 30, null);

        memberRepository.save(m1);
        memberRepository.save(m2);
        memberRepository.save(m3);
        memberRepository.save(m4);
        memberRepository.save(m5);
        memberRepository.save(m6);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("name222", 20);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getUsername()).isEqualTo("name222");


        // Like => %% 를 붙여주지 않음
        // Contains => pre, postfix 에 %% 를 붙여서 검색 (%_ 등의 특수문자는 알아서 escape)
        // IgnoreCase 키워드를 사용해 ILIKE 검색 가능
        memberRepository.findByUsernameLike("me1"); // like 'me1' escape '\';
        memberRepository.findByUsernameLike("%me1%"); // like '%me1%' escape '\';

        memberRepository.findByUsernameContains("me1"); // like '%me1%' escape '\';
        memberRepository.findByUsernameContains("%me1%"); // like '%\%me1\%%' escape '\';

        memberRepository.findByUsernameContainsIgnoreCase("ME1"); // like upper('%ME1%') escape '\';
    }

}