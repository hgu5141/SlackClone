package com.example.slack.controller;

import com.example.slack.dto.MessageDto;
import com.example.slack.model.Members;
import com.example.slack.repository.MembersRepository;
import com.example.slack.security.UserDetailsImpl;
import com.example.slack.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DmsController {

    private final MembersRepository membersRepository;
    private final MessageService messageService;

    @PostMapping("api/dms/message/{workId}")
    public void addMessage(@PathVariable Long workId, @RequestBody MessageDto messageDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        Optional<Members> members = membersRepository.findById(workId);
        if(messageDto.getMemberId() == members.get().getMemberId()){
            messageService.addMessage(messageDto, userDetails, workId);
        }
    }


}
