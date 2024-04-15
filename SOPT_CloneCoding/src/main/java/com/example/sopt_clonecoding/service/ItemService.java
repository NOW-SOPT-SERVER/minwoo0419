package com.example.sopt_clonecoding.service;

import com.example.sopt_clonecoding.domain.Item;
import com.example.sopt_clonecoding.domain.Member;
import com.example.sopt_clonecoding.dto.ItemCreateDto;
import com.example.sopt_clonecoding.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}