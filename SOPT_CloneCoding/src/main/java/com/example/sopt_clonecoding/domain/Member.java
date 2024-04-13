package com.example.sopt_clonecoding.domain;

import com.example.sopt_clonecoding.dto.MemberCreateDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "member")
    private List<Description> descriptions = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();
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
