package com.example.hg.repository.lecture;

import com.example.hg.model.member.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member find(Long id) {
        // Member member = em.find(Member.class, id);
        Member member = em.find(Member.class, id);
        return member;
    }

}
