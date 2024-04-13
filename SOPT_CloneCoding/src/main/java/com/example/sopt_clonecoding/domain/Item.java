package com.example.sopt_clonecoding.domain;

import com.example.sopt_clonecoding.dto.ItemCreateDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
    private String title;
    private Integer price;
    private String content;
    private boolean isSell;
    private boolean canOffer;
    private String place;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
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
