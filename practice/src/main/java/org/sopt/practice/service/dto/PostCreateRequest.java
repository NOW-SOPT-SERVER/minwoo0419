package org.sopt.practice.service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostCreateRequest(
        @NotBlank(message="제목을 입력해주세요.")
        @Size(max=50, message="글 제목이 최대 글자 수(50자)를 초과했습니다.")
        String title,
        @NotBlank(message="내용을 입력해주세요.")
        String content
) {
}
