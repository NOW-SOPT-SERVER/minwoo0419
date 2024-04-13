package org.sopt.practice.service.dto;

import org.sopt.practice.domain.Member;
import org.sopt.practice.domain.Part;

public record MemberDto(
        Long id,
        String name,
        Part part,
        int age
) {
    public static MemberDto of(
            Member member
    ){
        return new MemberDto(member.getId(), member.getName(), member.getPart(), member.getAge());
    }
}