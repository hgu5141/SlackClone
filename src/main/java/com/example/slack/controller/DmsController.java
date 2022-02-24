package com.example.slack.controller;

import com.example.slack.dto.ChatDto;
import com.example.slack.dto.ChatResponseDto;
import com.example.slack.model.Dms;
import com.example.slack.model.Members;
import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import com.example.slack.repository.DmsRepository;
import com.example.slack.repository.MembersRepository;
import com.example.slack.repository.UserRepository;
import com.example.slack.repository.WorkspacesRepository;
import com.example.slack.security.UserDetailsImpl;
import com.example.slack.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DmsController {

    private final MembersRepository membersRepository;
    private final MessageService messageService;
    private final WorkspacesRepository workspacesRepository;
    private final DmsRepository dmsRepository;
    private final UserRepository userRepository;

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

    @GetMapping("/api/dms/{workId}/{memberId}")
    public List<ChatResponseDto> getChat(@PathVariable Long workId, @PathVariable Long memberId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userRepository.findById(userDetails.getUser().getUserid()).orElseThrow(
                () -> new IllegalArgumentException("유저가 없어요")
        );
        Workspaces workspaces = workspacesRepository.findById(workId).orElseThrow(
                ()-> new IllegalArgumentException("웤스가 없어요")
        );
        Members members = membersRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("상대방이 없어요.")
        );


        List<Dms> dmsList = dmsRepository.findByWorkspacesAndUserAndMembers(workspaces, user,members);

        Optional<User> receiver = userRepository.findById(memberId);
        String memberNickname = receiver.get().getNickname();

        List<ChatResponseDto> chatResponseDtos = new ArrayList<>();

        for (Dms dm : dmsList) {
            ChatResponseDto chatResponseDto = new ChatResponseDto(
                    dm.getWorkspaces().getWorkId(),
                    dm.getUser().getNickname(),
                    dm.getChat(),
                    memberNickname
            );
            chatResponseDtos.add(chatResponseDto);
        }
        return chatResponseDtos;

    }
}
