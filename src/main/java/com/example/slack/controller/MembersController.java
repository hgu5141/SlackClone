package com.example.slack.controller;

import com.example.slack.dto.MembersRequestDto;
import com.example.slack.dto.MembersResponseDto;
import com.example.slack.dto.WorkInfoDto;
import com.example.slack.model.Members;
import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import com.example.slack.repository.MembersRepository;
import com.example.slack.repository.UserRepository;
import com.example.slack.repository.WorkspacesRepository;
import com.example.slack.security.UserDetailsImpl;
import com.example.slack.service.MembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MembersController {

    private final MembersService MembersService;
    private final MembersRepository membersRepository;
    private final UserRepository userRepository;
    private final WorkspacesRepository workspacesRepository;


    //초대
    @PostMapping("/api/members/{workId}")
    public Members addMember(
            @RequestBody MembersRequestDto MembersRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long workId) {

        return MembersService.addMember(workId, MembersRequestDto, userDetails);
    }


    //   //조회    List<Workspaces> findByUser(Optional<User> user);
    @GetMapping("/api/members/{workId}")
    public List<MembersResponseDto> getMembers(@PathVariable Long workId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<Workspaces> workspaces = workspacesRepository.findById(workId);
        List<Members> memberList = membersRepository.findByWorkspaces(workspaces);
        List<MembersResponseDto> membersResponseDtos = new ArrayList<>();
        for (Members members : memberList) {

            Long workId1 = members.getWorkspaces().getWorkId();
            String workName = members.getWorkspaces().getWorkName();

            Long memberId = members.getMemberId();
            String memberName = members.getUser().getNickname();
            MembersResponseDto membersResponseDto = new MembersResponseDto(workId1, workName, memberId, memberName);
            membersResponseDtos.add(membersResponseDto);
        }
        return membersResponseDtos;
    }

    //탈퇴
    @DeleteMapping("/api/members/{memberId}")
    public Long deleteMember(@PathVariable Long memberId) {
        MembersService.deleteMember(memberId);
        return memberId;
    }
}
