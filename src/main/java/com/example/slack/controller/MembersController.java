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
    @GetMapping("/api/dms/{workId}")
    public List<MembersResponseDto> getMembers(@PathVariable Long workId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<Workspaces> workspaces = workspacesRepository.findById(workId);
        List<Members> memberList = membersRepository.findByWorkspaces(workspaces);
        List<MembersResponseDto> membersResponseDtos = new ArrayList<>();
        for(Members members : memberList) {
            Long workId1 = members.getWorkspaces().getWorkId();
            String workName = members.getWorkspaces().getWorkName();
            Long memberId = members.getMemberId();
            String memberName = members.getMemberName();
            MembersResponseDto membersResponseDto = new MembersResponseDto(workId1, workNaame, memberId, memberName);
            membersResponseDtos.add(membersResponseDto);
        }
        return membersResponseDtos;

//        System.out.println("시작");
//        List<Optional<Members>> membersList = singletonList(membersRepository.findById(workId));
//        System.out.println(membersList);
//
//        List<MembersResponseDto> membersResponseDtos = new ArrayList<>();
//        for (Optional<Members> members : membersList) {
//            Long memberId = members.get().getMemberId();
//            String memberName = members.get().getMemberName();
//            Long work = members.get().getWorkspaces().getWorkId();
//            String workname = members.get().getWorkspaces().getWorkName();
//
//            System.out.println("트라이");
//            MembersResponseDto membersResponseDto = new MembersResponseDto(memberId, memberName, work, workname);
//            membersResponseDtos.add(membersResponseDto);

        }
//        return membersResponseDtos;


//        User user = userDetails.getUser();
//        Optional<Members> membersList = membersRepository.findByMemberName(user, workId);
//        System.out.println(membersList);
//        for(dms : dmsList)
//        String dmsNickName = dmsList.get().getUser().getNickname();
//        String dmsUsername = dmsList.get().getMemberName();
//
//        return null;
//
//
//    }
//        List<Members> dmsList = membersRepository.findAllById(Collections.singleton(userDetails.getUser().getUserid()));
////        Optional<User> memberNickname = userRepository.findByUsername(membersRepository.getMemberName());
//        List<MembersResponseDto> membersResponseDtos = new ArrayList<>();
//
//        for (Members members : dmsList) {
//           MembersResponseDto membersResponseDto = new MembersResponseDto(
//                    members.getWorkspaces().getWorkId(),
//                    members.getWorkspaces().getWorkName(),
//                    members.getMemberId(),
//                   members.getMemberName()
//            );
//
//            membersResponseDtos.add(membersResponseDto);
//        }
//        return membersResponseDtos;
//    }

    //탈퇴
    @DeleteMapping("/api/members/{memberId}")
    public Long deleteMember(@PathVariable Long memberId) {
        MembersService.deleteMember(memberId);
        return memberId;
    }
}
