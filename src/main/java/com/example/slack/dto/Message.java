package com.example.slack.dto;

import com.example.slack.model.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    private Long senderid;
    private Long receiverid;
    private String message;
    private String Status;
}
