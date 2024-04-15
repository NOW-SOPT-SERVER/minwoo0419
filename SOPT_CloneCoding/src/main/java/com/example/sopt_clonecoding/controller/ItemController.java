package com.example.sopt_clonecoding.controller;

import com.example.sopt_clonecoding.dto.common.ApiResponse;
import com.example.sopt_clonecoding.service.ItemService;
import com.example.sopt_clonecoding.dto.ItemCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/{memberId}/item")
public class ItemController {
    private final ItemService itemService;
    @PostMapping
    public ApiResponse<?> postItem(
            @PathVariable Long memberId,
            @RequestBody ItemCreateDto itemCreateDto
    ){
        return ApiResponse.created(itemService.createItem(memberId, itemCreateDto));
    }

}