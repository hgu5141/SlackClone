package com.example.slack.service;


import com.example.slack.dto.MembersRequestDto;
import com.example.slack.model.Members;
import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import com.example.slack.repository.MembersRepository;
import com.example.slack.repository.UserRepository;
import com.example.slack.repository.WorkspacesRepository;
import com.example.slack.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MembersService {

    private final MembersRepository membersRepository;
    private final WorkspacesRepository workspacesRepository;
    private final UserRepository userRepository;

    //초대
    @Transactional
    public Members addMember(
            Long workId,
            MembersRequestDto membersRequestDto,
            UserDetailsImpl userDetails){

        Workspaces workspaces = workspacesRepository.findById(workId).orElseThrow(
                () -> new IllegalArgumentException("해당 워크스페이스가 존재하지 않습니다.")
        );
        String member = membersRequestDto.getMemberName();
        if(member == null){
            throw new IllegalArgumentException("초대할 유저의 이메일을 입력해주세요.");
        }
        Optional<User> found = userRepository.findByUsername(member);
        Optional<Members> foundm = membersRepository.findByMemberNameAndWorkspaces(membersRequestDto.getMemberName(),workspaces);
        if(!found.isPresent()){
            throw new IllegalArgumentException("없는 이메일 주소입니다.");
        }
        if(foundm.isPresent()){
            throw new IllegalArgumentException("이미 워크스페이스 멤버입니다.");
        }

        User user = userDetails.getUser();

        Members members = new Members(membersRequestDto, workspaces, user);
        membersRepository.save(members);
        return members;

    }

    //삭제
    @Transactional
    public Long deleteMember(Long memberId){

        membersRepository.deleteById(memberId);
        return memberId;
    }
}
