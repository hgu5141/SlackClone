package com.example.slack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDto {
    Long memberId;
    String chat;
    String memberName;
}
