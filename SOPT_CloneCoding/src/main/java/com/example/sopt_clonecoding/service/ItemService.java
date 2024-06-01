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

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final MemberService memberService;
    private final ImageService imageService;
    private final S3Service s3Service;
    @Transactional
    public void createItem(Long memberId, ItemCreateDto itemCreateDto){
        Member member = memberService.findMemberById(memberId);
        Item item = itemRepository.save(Item.create(member, itemCreateDto));
        itemCreateDto.images().forEach(
                image -> {
                    try {
                        String imageUrl = s3Service.uploadImage("items/" + item.getId(), image);
                        imageService.createImage(imageUrl, item);
                    } catch (IOException e) {
                        throw new CustomException(ErrorCode.FAILED_UPLOAD_IMAGE);
                    }
                }
        );
    }

    @Transactional
    public void deleteItem(Long itemId){
        Item item = findItemById(itemId);
        imageService.findAllByItem(item).forEach(
                image -> {
                    try{
                        s3Service.deleteImage(image.getImageUrl());
                        imageService.removeImage(image);
                    } catch (IOException e) {
                        throw new CustomException(ErrorCode.FAILED_DELETE_IMAGE);
                    }
                }
        );
        itemRepository.delete(item);
    }

    public ItemListDto findAllByPlace(String place){
        List<ItemDto> items = itemRepository.findAllByPlace(place).stream().map(
                item -> ItemDto.of(item, item.getLikes().size())
        ).toList();
        return ItemListDto.of(items);
    }
    public Item findItemById(Long itemId){
        return itemRepository.findById(itemId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_ITEM)
        );
    }
}