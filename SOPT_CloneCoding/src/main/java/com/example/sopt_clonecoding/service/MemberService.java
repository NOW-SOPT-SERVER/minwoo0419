package com.example.sopt_clonecoding.service;

import com.example.sopt_clonecoding.domain.Member;
import com.example.sopt_clonecoding.dto.MemberCreateDto;
import com.example.sopt_clonecoding.dto.type.ErrorCode;
import com.example.sopt_clonecoding.exception.CustomException;
import com.example.sopt_clonecoding.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Member findMemberById(Long memberId){
        return  memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
    }
    @Transactional
    public Long createMember(MemberCreateDto memberCreateDto){
        Member member = Member.create(memberCreateDto);
        memberRepository.save(member);
        return member.getId();
    }
}
