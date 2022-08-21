package com.example.hg.repository.lecture;

import com.example.hg.model.lecture.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    // JPQL 사용
    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age) {
        return em.createQuery("select m from Member m where m.username = :username and m.age > :age")
            .setParameter("username", username)
            .setParameter("age", age)
            .getResultList();
    }

    // jpa에서의 NamedQuery 사용방법 (spring data jpa 방법은 MemberRepositoy. findByUsername 확인)
    public List<Member> findByUsernameUsingNamedQuery(String username) {
        return em.createNamedQuery("Member.findByUsername", Member.class)
            .setParameter("username", username)
            .getResultList();
    }

}
