package com.example.slack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatResponseDto {

    private final Long workId;
    private final String chat;
    private final String nickname;
    private final String memberNickname;

    public ChatResponseDto(Long workId, String nickname, String chat, String memberNickname) {
        this.workId = workId;
        this.nickname = nickname;
        this.chat = chat;
        this.memberNickname = memberNickname;

    }
}