package com.example.slack.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberWorkDto {
    private String memberName;

    public MemberWorkDto(String memberName) {
        this.memberName = memberName;
    }
}
