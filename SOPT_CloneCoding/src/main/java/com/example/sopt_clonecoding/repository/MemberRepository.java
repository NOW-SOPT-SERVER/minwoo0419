package com.example.sopt_clonecoding.repository;

import com.example.sopt_clonecoding.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
