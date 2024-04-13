package org.sopt.practice.service.dto;

import java.util.List;

public record MemberListDto(
        List<MemberDto> members

) {
    public static MemberListDto of(
            List<MemberDto> members
    ){
        return new MemberListDto(members);
    }
}
