package com.example.hg.repository;

import com.example.hg.model.lecture.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
