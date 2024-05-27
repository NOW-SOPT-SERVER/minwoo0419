package org.sopt.practice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.service.AuthService;
import org.sopt.practice.service.dto.MemberCreateDto;
import org.sopt.practice.service.dto.UserJoinResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/auth/sign-up")
    public ResponseEntity<UserJoinResponse> signUp(
            @RequestBody MemberCreateDto memberCreate
    ) {
        UserJoinResponse userJoinResponse = authService.signUp(memberCreate);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", userJoinResponse.refreshToken())
                .body(
                        userJoinResponse
                );
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<UserJoinResponse> signIn(
            @RequestHeader Long memberId //아이디, 비밀번호 없으므로 임시 사용
    ) {
        UserJoinResponse userJoinResponse = authService.signIn(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        userJoinResponse
                );
    }

    @PostMapping("/auth/re-issue")
    public ResponseEntity<UserJoinResponse> reIssue(HttpServletRequest request) {
        return ResponseEntity.ok(authService.reIssue(request));
    }
}
