package com.example.sopt_clonecoding.dto;

import jakarta.annotation.Nullable;

public record ItemCreateDto(
        String title,
        @Nullable
        Integer price,
        String content,
        boolean isSell,
        boolean canOffer,
        String place
) {
}
