package org.sopt.practice.service.dto;

public record UserJoinResponse(
        String accessToken,
        String refreshToken
) {

    public static UserJoinResponse of(
            String accessToken,
            String refreshToken
    ) {
        return new UserJoinResponse(accessToken, refreshToken);
    }
}