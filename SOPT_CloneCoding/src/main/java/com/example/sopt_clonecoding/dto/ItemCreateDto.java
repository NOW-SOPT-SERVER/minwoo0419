package com.example.sopt_clonecoding.dto;

import com.example.sopt_clonecoding.dto.type.Tag;
import jakarta.annotation.Nullable;

public record ItemCreateDto(
        String title,
        Tag tag,
        @Nullable
        String tagContent,
        @Nullable
        Integer price,
        String content,
        boolean isSell,
        boolean canOffer,
        @Nullable
        String place
) {
}
