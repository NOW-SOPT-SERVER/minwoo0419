package com.example.sopt_clonecoding.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public static Description create(Member member, String content){
        return Description.builder()
                .member(member)
                .content(content)
                .build();
    }
    @Builder
    public Description(Member member, String content){
        this.member = member;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
