package com.example.sopt_clonecoding.domain;

import com.example.sopt_clonecoding.dto.ItemCreateDto;
import com.example.sopt_clonecoding.dto.type.Tag;
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
    @Enumerated(EnumType.STRING)
    @Column(name="tag", nullable=false)
    private Tag tag;
    @Column(name="tag_content")
    private String tagContent;
    @Column(name="price", columnDefinition="integer default 0")
    private Integer price;
    @Column(name="content", nullable=false)
    private String content;
    @Column(name="is_sell", nullable=false)
    private boolean isSell;
    @Column(name="can_offer", nullable=false)
    private boolean canOffer;
    @Column(name="place")
    private String place;
    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;
    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt;
    @ManyToOne(targetEntity=Member.class, fetch=FetchType.LAZY)
    @JoinColumn(name="member_id", nullable=false)
    private Member member;
    public static Item create(Member member, ItemCreateDto itemCreateDto){
        return Item.builder()
                .member(member)
                .title(itemCreateDto.title())
                .tag(itemCreateDto.tag())
                .tagContent(itemCreateDto.tagContent())
                .price(itemCreateDto.price())
                .content(itemCreateDto.content())
                .isSell(itemCreateDto.isSell())
                .canOffer(itemCreateDto.canOffer())
                .place(itemCreateDto.place())
                .build();
    }

    @Builder
    public Item(Member member, String title, Tag tag, String tagContent, Integer price, String content, boolean isSell, boolean canOffer, String place) {
        this.member = member;
        this.title = title;
        this.tag = tag;
        this.tagContent = tagContent;
        this.price = price;
        this.content = content;
        this.isSell = isSell;
        this.canOffer = canOffer;
        this.place = place;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
