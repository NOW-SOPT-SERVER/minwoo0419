package com.example.sopt_clonecoding.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="image")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Image {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne(targetEntity=Item.class, fetch= FetchType.LAZY)
    private Item item;

    private LocalDateTime createdAt;

    public static Image create(String imageUrl, Item item){
        return new Image(imageUrl, item);
    }

    public Image(String imageUrl, Item item) {
        this.imageUrl = imageUrl;
        this.item = item;
        this.createdAt = LocalDateTime.now();
    }
}
