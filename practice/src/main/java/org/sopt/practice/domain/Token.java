package org.sopt.practice.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value="", timeToLive = 60 * 60 * 24 * 1000L * 14)
@Getter
@Builder
public class Token {
    @Id
    private Long id;

    @Indexed
    private String refreshToken;

    public static Token create(
            final Long id,
            final String refreshToken
    ) {
        return Token.builder()
                .id(id)
                .refreshToken(refreshToken)
                .build();
    }
}
