package org.sopt.practice.service.dto;

import org.sopt.practice.domain.Post;

public record PostDto(
        Long id,
        String title
) {
    public static PostDto of(
            Post post
    ){
        return new PostDto(post.getId(), post.getTitle());
    }
}
