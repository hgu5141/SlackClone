package com.example.slack.service;

import com.example.slack.dto.ChatDto;
import com.example.slack.model.Dms;
import com.example.slack.model.Members;
import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import com.example.slack.repository.DmsRepository;
import com.example.slack.repository.MembersRepository;
import com.example.slack.repository.WorkspacesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final DmsRepository dmsRepository;
    private final WorkspacesRepository workspacesRepository;
    private final MembersRepository membersRepository;


    public void addMessage(ChatDto chatDto, Workspaces workspaces , Members members , User user){
        System.out.println(chatDto.getChat());

        String chat = chatDto.getChat();
        Dms dms = new Dms(members, user, workspaces, chat);
        dmsRepository.save(dms);
    }
}
