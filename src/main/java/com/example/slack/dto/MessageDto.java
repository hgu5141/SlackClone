package com.example.slack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {
    Long memberId;
    String message;
}
