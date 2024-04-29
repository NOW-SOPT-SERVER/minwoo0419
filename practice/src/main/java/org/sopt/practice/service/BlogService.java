package org.sopt.practice.service;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.domain.Blog;
import org.sopt.practice.domain.Member;
import org.sopt.practice.exception.CustomAccessDeniedException;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.repository.BlogRepository;
import org.sopt.practice.service.dto.BlogCreateRequest;
import org.sopt.practice.service.dto.BlogTitleUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final MemberService memberService;

    public String create(Long memberId, BlogCreateRequest createRequest){
        Member member = memberService.findById(memberId);
        Blog blog = blogRepository.save(Blog.create(member, createRequest.title(), createRequest.description()));
        return blog.getId().toString();
    }
    @Transactional
    public void updateTitle(Long blogId, BlogTitleUpdateRequest blogTitleUpdateRequest){
        Blog blog = findById(blogId);
        blog.updateTitle(blogTitleUpdateRequest);
    }
    public Blog findById(Long blogId){
        return blogRepository.findById(blogId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND)
        );
    }
    public Blog validMember(Long blogId, Long memberId){
        Blog blog = findById(blogId);
        if (!blog.getMember().getId().equals(memberId))
            throw new CustomAccessDeniedException(ErrorMessage.BLOG_FORBIDDEN);
        return blog;
    }
}