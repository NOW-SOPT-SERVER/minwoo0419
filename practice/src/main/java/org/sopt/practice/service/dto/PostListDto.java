package org.sopt.practice.service.dto;

import java.util.List;

public record PostListDto(
        List<PostDto> posts
) {
    public static PostListDto of(List<PostDto> posts){
        return new PostListDto(posts);
    }
}
