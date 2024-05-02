package com.example.sopt_clonecoding.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="likes")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Like {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;
    @ManyToOne(targetEntity=Member.class, fetch=FetchType.LAZY)
    @JoinColumn(name="member_id", nullable=false)
    private Member member;
    @ManyToOne(targetEntity=Item.class, fetch=FetchType.LAZY)
    @JoinColumn(name="item_id", nullable=false)
    private Item item;

    public static Like create(Member member, Item item){
        return new Like(member, item);
    }

    @Builder
    public Like(Member member, Item item) {
        this.createdAt = LocalDateTime.now();
        this.member = member;
        this.item = item;
    }
}
