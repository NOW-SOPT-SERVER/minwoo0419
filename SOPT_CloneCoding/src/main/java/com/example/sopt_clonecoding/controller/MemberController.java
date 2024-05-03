package com.example.sopt_clonecoding.controller;

import com.example.sopt_clonecoding.dto.member.request.MemberCreateDto;
import com.example.sopt_clonecoding.dto.common.ApiResponse;
import com.example.sopt_clonecoding.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ApiResponse<Void> postMember(
            @RequestBody MemberCreateDto memberCreateDto
    ){
        memberService.createMember(memberCreateDto);
        return ApiResponse.created(null);
    }
}