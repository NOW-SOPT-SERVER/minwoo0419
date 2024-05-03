package com.example.sopt_clonecoding.dto.item.response;

import com.example.sopt_clonecoding.domain.Item;
import java.time.LocalDateTime;

public record ItemDto(
        Long id,
        String title,
        Integer price,
        LocalDateTime createdAt,
        Integer likeCount
) {
    public static ItemDto of(Item item, Integer likeCount){
        return new ItemDto(item.getId(), item.getTitle(), item.getPrice(), item.getCreatedAt(), likeCount);
    }
}
