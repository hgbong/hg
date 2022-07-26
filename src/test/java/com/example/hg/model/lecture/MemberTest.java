package com.example.hg.model.lecture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testEntity() {

        Team team1 = new Team("testteam1");
        Team team2 = new Team("testteam2");

        // 참고로, 만약 team이 영속화 (save) 되지 않고, 이를 참조하는 member만 영속화되는 경우 아레 오류 발생
        // cascade = CascadeType.PERSIST 등으로 지정해주면, member 영속화 시 team도 자동으로 영속화되지만, 명시적으로 team도 영속화하는 것이 좋을 듯
        // object references an unsaved transient instance - save the transient instance before flushing
        em.persist(team1);
        em.persist(team2);

        Member member1 = new Member("testname1", 10, team1);
        Member member2 = new Member("testname2", 20, team1);
        Member member3 = new Member("testname3", 30, team2);
        Member member4 = new Member("testname4", 40, team2);


        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        // flush 및 초기화
        em.flush();
        em.clear();

        // jpql로 db 데이터 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        // "iter" shortcut
        // Assertions.assertThat 으로 검증해도 됨
        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println("member.team = " + member.getTeam());
            System.out.println();
        }
    }
}