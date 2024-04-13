package com.example.sopt_clonecoding.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="description")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="member_id", nullable=false)
    private Member member;
    @Column(name="content", nullable=false)
    private String content;
    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;
    @Column(name="updated_at", nullable=false)
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
