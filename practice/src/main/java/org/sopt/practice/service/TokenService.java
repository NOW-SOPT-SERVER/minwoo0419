package org.sopt.practice.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.auth.UserAuthentication;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.common.jwt.JwtTokenProvider;
import org.sopt.practice.domain.Token;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.repository.TokenRepository;
import org.sopt.practice.service.dto.UserJoinResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Token save(
            final Long memberId,
            final String refreshToken
    ){
        Token token = Token.create(memberId, refreshToken);
        tokenRepository.save(token);
        return token;
    }

    @Transactional
    public UserJoinResponse createTokens(final Long memberId){
        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        String refreshToken = jwtTokenProvider.issueRefreshToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        Token token = save(memberId, refreshToken);
        return UserJoinResponse.of(accessToken, token.getRefreshToken());
    }

    public UserJoinResponse createAccessToken(final HttpServletRequest request){
        Token token = findByRefreshToken(jwtTokenProvider.getJwtFromRequest(request));
        Long memberId = token.getId();
        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        return UserJoinResponse.of(accessToken, token.getRefreshToken());
    }
    public Token findByRefreshToken(final String refreshToken){
        return tokenRepository.findByRefreshToken(refreshToken).orElseThrow(
                () -> new NotFoundException(ErrorMessage.TOKEN_NOT_FOUND)
        );
    }
}
