package org.sopt.practice.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.domain.Member;
import org.sopt.practice.service.dto.MemberCreateDto;
import org.sopt.practice.service.dto.UserJoinResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberService memberService;
    private final TokenService tokenService;
    @Transactional
    public UserJoinResponse signUp(
            final MemberCreateDto memberCreate
    ) {
        Long memberId = memberService.save(
                Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age())
        );
        return tokenService.createTokens(memberId);
    }
    @Transactional
    public UserJoinResponse signIn(
            final Long memberId
    ) {
        return tokenService.createTokens(memberId);

    }
    public UserJoinResponse reIssue(final HttpServletRequest request){
        return tokenService.createAccessToken(request);
    }
}
