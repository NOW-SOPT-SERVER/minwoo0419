package com.example.sopt_clonecoding.dto.item.request;

import com.example.sopt_clonecoding.dto.type.Tag;
import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        String place,

        List<MultipartFile> images
) {
}