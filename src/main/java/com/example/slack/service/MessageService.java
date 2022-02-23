package com.example.slack.service;

import com.example.slack.dto.MessageDto;
import com.example.slack.model.Dms;
import com.example.slack.model.Members;
import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import com.example.slack.repository.DmsRepository;
import com.example.slack.repository.MembersRepository;
import com.example.slack.repository.WorkspacesRepository;
import com.example.slack.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final DmsRepository dmsRepository;
    private final WorkspacesRepository workspacesRepository;
    private final MembersRepository membersRepository;


    public void addMessage(MessageDto messageDto, UserDetailsImpl userDetails, Long workId){

        Long memberId = messageDto.getMemberId();
        Optional<Members> members = membersRepository.findById(memberId);
        Optional<Workspaces> workspaces = workspacesRepository.findById(workId);
        User user = userDetails.getUser();
        String message = messageDto.getMessage();
        Dms dms = new Dms(members, user, workspaces, message);
        dmsRepository.save(dms);
    }
}
