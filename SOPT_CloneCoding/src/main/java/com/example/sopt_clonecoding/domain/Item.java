package com.example.sopt_clonecoding.domain;

import com.example.sopt_clonecoding.dto.ItemCreateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="item")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="title", nullable=false)
    private String title;
    @Column(name="tag", nullable = false)
    
    @Column(name="price")
    private Integer price;
    @Column(name="content", nullable=false)
    private String content;
    @Column(name="is_sell", nullable=false)
    private boolean isSell;
    @Column(name="can_offer", nullable=false)
    private boolean canOffer;
    @Column(name="place")
    private String place;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne(targetEntity=Member.class, fetch=FetchType.LAZY)
    @JoinColumn(name="member_id", nullable=false)
    private Member member;
    public static Item create(Member member, ItemCreateDto itemCreateDto){
        return Item.builder()
                .member(member)
                .title(itemCreateDto.title())
                .price(itemCreateDto.price())
                .content(itemCreateDto.content())
                .isSell(itemCreateDto.isSell())
                .canOffer(itemCreateDto.canOffer())
                .place(itemCreateDto.place())
                .build();
    }

    @Builder
    public Item(Member member, String title, Integer price, String content, boolean isSell, boolean canOffer, String place) {
        this.member = member;
        this.title = title;
        this.price = price;
        this.content = content;
        this.isSell = isSell;
        this.canOffer = canOffer;
        this.place = place;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
