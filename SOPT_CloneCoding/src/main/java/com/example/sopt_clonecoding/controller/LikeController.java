package com.example.sopt_clonecoding.controller;

import com.example.sopt_clonecoding.dto.common.ApiResponse;
import com.example.sopt_clonecoding.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items/{itemId}")
public class LikeController {
    private final LikeService likeService;
    @PostMapping("/likes")
    public ApiResponse<Void> postLike(
            @RequestHeader Long memberId,
            @PathVariable Long itemId
    ){
        likeService.createLike(memberId, itemId);
        return ApiResponse.created(null);
    }

    @DeleteMapping("/likes")
    public ApiResponse<Void> deleteLike(
            @RequestHeader Long memberId,
            @PathVariable Long itemId
    ){
        likeService.deleteLike(memberId, itemId);
        return ApiResponse.ok(null);
    }
}
