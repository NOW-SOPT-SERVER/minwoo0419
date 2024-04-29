package org.sopt.practice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.domain.Blog;
import org.sopt.practice.domain.Post;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.repository.PostRepository;
import org.sopt.practice.service.dto.PostCreateRequest;
import org.sopt.practice.service.dto.PostDto;
import org.sopt.practice.service.dto.PostFindDto;
import org.sopt.practice.service.dto.PostListDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BlogService blogService;

    @Transactional
    public String create(Long blogId, Long memberId,PostCreateRequest postCreateRequest){
        Blog blog = blogService.validMember(blogId, memberId);
        Post post = postRepository.save(Post.create(postCreateRequest, blog));
        return post.getId().toString();
    }

    public PostFindDto findPost(Long postId){
        Post post = findById(postId);
        return PostFindDto.of(post);
    }
    public PostListDto findAllByBlogId(Long blogId){
        Blog blog = blogService.findById(blogId);
        List<PostDto> posts = postRepository.findAllByBlog(blog)
                .stream().map(
                        PostDto::of
                ).toList();
        return PostListDto.of(posts);
    }
    public Post findById(Long postId){
        return postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.POST_NOT_FOUND)
        );
    }
}
