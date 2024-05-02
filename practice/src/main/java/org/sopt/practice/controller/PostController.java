package org.sopt.practice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.SuccessMessage;
import org.sopt.practice.common.dto.SuccessStatusResponse;
import org.sopt.practice.service.PostService;
import org.sopt.practice.service.dto.PostCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping("/post")
    public ResponseEntity<SuccessStatusResponse> createPost(
            @RequestHeader(name="blogId") Long blogId,
            @RequestHeader(name="memberId") Long memberId,
            @Valid @RequestBody PostCreateRequest postCreateRequest
    ){
        return ResponseEntity.status(HttpStatus.CREATED).header(
                "Location",
                postService.create(blogId, memberId, postCreateRequest))
                .body(SuccessStatusResponse.of(SuccessMessage.POST_CREATE_SUCCESS, null));
    }

    @GetMapping("/post")
    public ResponseEntity<SuccessStatusResponse> findAllByBlogId(
            @RequestHeader Long blogId
    ){
        return ResponseEntity.ok(SuccessStatusResponse.of(SuccessMessage.POST_GET_SUCCESS ,postService.findAllByBlogId(blogId)));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<SuccessStatusResponse> findById(
            @PathVariable Long postId
    ){
        return ResponseEntity.ok(SuccessStatusResponse.of(SuccessMessage.POST_GET_SUCCESS, postService.findPost(postId)));
    }
}
