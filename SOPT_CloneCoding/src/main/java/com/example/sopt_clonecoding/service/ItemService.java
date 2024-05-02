package com.example.sopt_clonecoding.service;

import com.example.sopt_clonecoding.domain.Item;
import com.example.sopt_clonecoding.domain.Member;
import com.example.sopt_clonecoding.dto.item.request.ItemCreateDto;
import com.example.sopt_clonecoding.dto.item.response.ItemDto;
import com.example.sopt_clonecoding.dto.item.response.ItemListDto;
import com.example.sopt_clonecoding.dto.type.ErrorCode;
import com.example.sopt_clonecoding.exception.CustomException;
import com.example.sopt_clonecoding.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final MemberService memberService;
    @Transactional
    public Long createItem(Long memberId, ItemCreateDto itemCreateDto){
        Member member = memberService.findMemberById(memberId);
        Item item = Item.create(member, itemCreateDto);
        itemRepository.save(item);
        return item.getId();
    }

    public ItemListDto findAllByPlace(String place){
        List<ItemDto> items = itemRepository.findAllByPlace(place).stream().map(
                item -> ItemDto.of(item, item.getLikes().stream().count())
        ).toList();
        return ItemListDto.of(items);
    }
    public Item findItemById(Long itemId){
        return itemRepository.findById(itemId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_ITEM)
        );
    }
}