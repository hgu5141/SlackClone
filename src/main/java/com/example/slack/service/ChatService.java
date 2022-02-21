package com.example.slack.service;

import com.example.slack.dto.Chatting;
import com.example.slack.dto.Message;
import com.example.slack.repository.ChatRepository;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ChatService {


    public void save(Message message) {
        Chatting chatting = new Chatting(message);
    }
}
