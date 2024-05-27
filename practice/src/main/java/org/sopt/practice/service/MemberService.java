package org.sopt.practice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.domain.Member;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.dto.MemberDto;
import org.sopt.practice.service.dto.MemberFindDto;
import org.sopt.practice.service.dto.MemberListDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(Member member){
        return memberRepository.save(member).getId();
    }
    @Transactional(readOnly = true)
    public MemberFindDto findMemberById(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")
        );
        return MemberFindDto.of(member);
    }
    @Transactional
    public void deleteMemberById(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")
        );
        memberRepository.delete(member);
    }
    @Transactional(readOnly = true)
    public MemberListDto findAllMember(){
        List<MemberDto> members = memberRepository.findAll().stream().map(
                MemberDto::of
        ).toList();
        return MemberListDto.of(members);
    }
    public Member findById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND)
        );
    }
}
