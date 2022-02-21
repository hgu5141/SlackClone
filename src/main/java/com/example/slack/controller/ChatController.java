package com.example.slack.controller;

import com.example.slack.dto.Chatting;
import com.example.slack.repository.ChatRepository;
import com.example.slack.repository.RoomRepository;
import com.example.slack.security.UserDetailsImpl;
import com.example.slack.service.UserService;
import lombok.Getter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class ChatController {

    private UserService userService;
    private ChatRepository chatRepository;
    private SimpMessagingTemplate webSocket;
    private RoomRepository roomRepository;


    //chatID가 와야함
    @GetMapping("/chat/{workspaceId}")
    public String Chatting(@PathVariable Long workspaceId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String user = userDetails.getUsername();
        String result = String.valueOf(roomRepository.findById(user, workspaceId));
        return result;
    }

}
