package org.sopt.practice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.domain.Member;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.dto.MemberCreateDto;
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
    public String createMember(MemberCreateDto memberCreateDto){
        Member member = Member.create(memberCreateDto.name(), memberCreateDto.part(), memberCreateDto.age());
        memberRepository.save(member);
        return member.getId().toString();
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
}
