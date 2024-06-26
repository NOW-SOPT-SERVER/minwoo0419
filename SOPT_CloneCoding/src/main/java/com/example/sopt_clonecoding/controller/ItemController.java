package com.example.sopt_clonecoding.controller;

import com.example.sopt_clonecoding.dto.common.ApiResponse;
import com.example.sopt_clonecoding.dto.item.response.ItemListDto;
import com.example.sopt_clonecoding.service.ItemService;
import com.example.sopt_clonecoding.dto.item.request.ItemCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemController {
    private final ItemService itemService;
    @PostMapping("/items")
    public ApiResponse<Void> postItem(
            @RequestHeader Long memberId,
            @RequestBody ItemCreateDto itemCreateDto
    ){
        itemService.createItem(memberId, itemCreateDto);
        return ApiResponse.created(null);
    }
    @GetMapping("/items")
    public ApiResponse<ItemListDto> findAllByPlace(
            @RequestParam String place
    ){
        return ApiResponse.ok(itemService.findAllByPlace(place));
    }

}