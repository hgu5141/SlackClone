package com.example.slack.controller;

import com.example.slack.dto.ChatDto;
import com.example.slack.model.Members;
import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import com.example.slack.repository.MembersRepository;
import com.example.slack.repository.WorkspacesRepository;
import com.example.slack.security.UserDetailsImpl;
import com.example.slack.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class DmsController {

    private final MembersRepository membersRepository;
    private final MessageService messageService;
    private final WorkspacesRepository workspacesRepository;

    @PostMapping("api/dms/{workId}")
    public void addMessage(@PathVariable Long workId, @RequestBody ChatDto messageDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("1");
        Workspaces workspaces = workspacesRepository.findById(workId).orElseThrow(()-> new IllegalArgumentException("워크스페이 조회 X"));
        String memberName = messageDto.getMemberName();
        Members members = membersRepository.findByMemberNameAndWorkspaces(memberName, workspaces).orElseThrow(()-> new IllegalArgumentException("조회불가")); //워크스페이스 상의 멤버들 리스트화
        System.out.println(workspaces);
        User user = userDetails.getUser();
        System.out.println("3");
        messageService.addMessage(messageDto, workspaces, members, user);
    }
}
