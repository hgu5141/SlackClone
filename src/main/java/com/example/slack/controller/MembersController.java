package com.example.slack.controller;

import com.example.slack.dto.BookmarkResponseDto;
import com.example.slack.dto.MembersRequestDto;
import com.example.slack.dto.MembersResponseDto;
import com.example.slack.model.Bookmark;
import com.example.slack.model.Members;
import com.example.slack.model.User;
import com.example.slack.repository.MembersRepository;
import com.example.slack.repository.UserRepository;
import com.example.slack.repository.WorkspacesRepository;
import com.example.slack.security.UserDetailsImpl;
import com.example.slack.service.MembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MembersController {

    private final MembersService MembersService;
    private final MembersRepository membersRepository;
    private final UserRepository userRepository;


    //초대
    @PostMapping("/api/members/{workId}")
    public Members addMember(
            @RequestBody MembersRequestDto MembersRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long workId) {

        return MembersService.addMember(workId, MembersRequestDto, userDetails);
    }

    //조회
    @GetMapping("/api/dms/{workId}")
    public List<MembersResponseDto> getMembers(@PathVariable Long workId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<Members> dmsList = membersRepository.findAllById(Collections.singleton(userDetails.getUser().getUserid()));
        Optional<User> memberNickname = userRepository.findByUsername(membersRepository.getMemberName());
        List<MembersResponseDto> membersResponseDtos = new ArrayList<>();

        for (Members members : dmsList) {
           MembersResponseDto membersResponseDto = new MembersResponseDto(
                    members.getWorkspaces().getWorkId(),
                    members.getWorkspaces().getWorkName(),
                    members.getMemberId(),
                   members.getMemberName()
            );

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
