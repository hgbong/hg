package com.example.hg.repository;

import com.example.hg.model.lecture.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {

    // spring data jpa의 메소드 정의방법
    // 1) query method
    // 2) named query
    // 3) query 직접 적용


    // Repository에서의 메소드 생성 우선순의
    // 1. NameQuery 메소드 존재하는지 확인   @Query(name="...")로 선언된 부분
    // 2. NameQuery (implicit) 메소드 존재 확인   -> @Query가 없는 경우 메소드명으로 NamedQuery를 찾는데, 이때 발견되는 게 있는지 확인
    // 3. 쿼리 메소드 방식으로 생성 가능한지 확인


    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    // 1) Query Method 방식
    List<Member> findByUsernameLike(String username);

    List<Member> findByUsernameContains(String username);

    // List<Member> findBy => 전체조회

    List<Member> findByUsernameContainsIgnoreCase(String username);

    // 2)spring data jpa에서의 NamedQuery 사용방법
    // 메소드명은 임의로 작성 가능하며, @Param 작성은 필수. (named paramter 넘어가는 경우라면 @Param 지정 필요)
    // @Query(name = "Member.findByUsername")  // => @Query 주석처리된 경우 메소드명에 해당하는 NamedQuery가 있는지 찾음
    List<Member> findByUsername(@Param("username") String username);

    // 3) @Query에 쿼리 직접 작성 방식
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findByUsername2(@Param("username") String username, @Param("age") String age);

}