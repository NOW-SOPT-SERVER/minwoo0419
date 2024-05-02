package com.example.sopt_clonecoding.dto.item.response;

import java.util.List;

public record ItemListDto(
        List<ItemDto> items
) {
    public static ItemListDto of(List<ItemDto> items){
        return new ItemListDto(items);
    }
}
