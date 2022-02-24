package com.example.slack.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatreturnDto {
    String memberNickname;

    public ChatreturnDto(String memberNickname, List<ChatResponseDto> chatResponseDtos) {
        this.memberNickname = memberNickname;
        this.chatResponseDtos = chatResponseDtos;
    }

    List<ChatResponseDto> chatResponseDtos;
}
