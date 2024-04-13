package org.sopt.practice.controller;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.MemberService;
import org.sopt.practice.service.dto.MemberCreateDto;
import org.sopt.practice.settings.ApiTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.sopt.practice.domain.Part.SERVER;

public class MemberControllerTest extends ApiTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Nested
    @DisplayName("멤버 생성 테스트")
    public class CreateMember{
        @Test
        @DisplayName("요청 성공 테스트")
        public void createMemberSuccess() throws Exception{
            //given
            final var request = new MemberCreateDto(
                    "조민우",
                    SERVER,
                    25
            );

            //when
            final var response = RestAssured
                    .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(request)
                    .when()
                    .post("/api/v1/member")
                    .then().log().all().extract();

            //then
            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        }
    }
}
