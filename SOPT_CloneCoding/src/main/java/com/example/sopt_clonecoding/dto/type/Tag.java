package com.example.sopt_clonecoding.dto.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tag {
    DIGITAL("디지털기기"),
    APPLIANCE("생활가전"),
    FURNITURE("가구/인테리어"),
    KITCHEN("생활/주방"),
    CHILDCARE("유아동"),
    SPORT("스포츠/레저"),
    BOOK("도서");

    private final String content;
}
