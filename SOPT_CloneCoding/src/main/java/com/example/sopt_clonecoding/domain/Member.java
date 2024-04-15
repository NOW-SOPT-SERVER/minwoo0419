package com.example.sopt_clonecoding.domain;

import com.example.sopt_clonecoding.dto.MemberCreateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Table(name="member")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;
    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "member", cascade=CascadeType.ALL)
    private final List<Description> descriptions = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade=CascadeType.ALL)
    private final List<Item> items = new ArrayList<>();
    public static Member create(MemberCreateDto memberCreateDto){
        return Member.builder()
                .name(memberCreateDto.name())
                .build();
    }

    @Builder
    public Member(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}