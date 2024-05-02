package com.example.sopt_clonecoding.repository;

import com.example.sopt_clonecoding.domain.Item;
import com.example.sopt_clonecoding.domain.Like;
import com.example.sopt_clonecoding.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMemberAndItem(Member member, Item item);
    boolean existsByMemberAndItem(Member member, Item item);
}
