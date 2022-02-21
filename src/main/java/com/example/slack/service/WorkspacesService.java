package com.example.slack.service;


import com.example.slack.dto.WorkspacesRequestDto;
import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import com.example.slack.repository.WorkspacesRepository;
import com.example.slack.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service

public class WorkspacesService {
    private final WorkspacesRepository workspacesRepository;

    //워크스페이스 생성
    @Transactional
    public Workspaces createWs(
            WorkspacesRequestDto workspacesRequestDto, User user) {

        String workName = workspacesRequestDto.getWorkName();
        if(workspacesRequestDto.getWorkName() == null){
            throw new IllegalArgumentException("워크스페이스 이름을 입력해주세요.");
        }

        Workspaces workspaces = new Workspaces(workspacesRequestDto, user);
        return workspacesRepository.save(workspaces);
    }

    //워크스페이스 삭제
    @Transactional
    public Long deleteWs(Long workId, UserDetailsImpl userDetails){
        Workspaces workspaces = workspacesRepository.findById(workId).orElseThrow(
                () -> new IllegalArgumentException("워크스페이스가 존재하지 않습니다.")
        );
        User user = workspaces.getUser();
        Long deleteId = user.getUserid();
        if(!Objects.equals(userDetails.getUser().getUserid(),deleteId)){
            throw new IllegalArgumentException("개설자만 삭제할 수 있습니다.");
        }

        workspacesRepository.deleteById(workId);
        return workId;
    }
}
