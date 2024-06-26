package com.example.sopt_clonecoding.dto.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 404 Not Found
    NOT_FOUND_END_POINT(40400, HttpStatus.NOT_FOUND, "존재하지 않는 API입니다."),
    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    // Custom Error
    NOT_FOUND_MEMBER(40401, HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    NOT_FOUND_ITEM(40411, HttpStatus.NOT_FOUND, "존재하지 않는 물건입니다."),
    NOT_FOUND_LIKE(40421, HttpStatus.NOT_FOUND, "좋아요가 존재하지 않습니다.");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
}
