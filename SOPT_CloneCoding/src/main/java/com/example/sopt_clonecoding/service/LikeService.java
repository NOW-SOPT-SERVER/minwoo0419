package com.example.sopt_clonecoding.service;

import com.example.sopt_clonecoding.domain.Item;
import com.example.sopt_clonecoding.domain.Like;
import com.example.sopt_clonecoding.domain.Member;
import com.example.sopt_clonecoding.dto.type.ErrorCode;
import com.example.sopt_clonecoding.exception.CustomException;
import com.example.sopt_clonecoding.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberService memberService;
    private final ItemService itemService;

    public void createLike(Long memberId, Long itemId){
        Member member = memberService.findMemberById(memberId);
        Item item = itemService.findItemById(itemId);
        if (likeRepository.existsByMemberAndItem(member, item))
            return;
        Like like = Like.create(member, item);
        likeRepository.save(like);
    }
    public void deleteLike(Long memberId, Long itemId){
        Member member = memberService.findMemberById(memberId);
        Item item = itemService.findItemById(itemId);
        Like like = likeRepository.findByMemberAndItem(member, item).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_LIKE)
        );
        likeRepository.delete(like);
    }
}
