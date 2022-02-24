package com.example.slack.service;

import com.example.slack.dto.SignupRequestDto;
import com.example.slack.dto.WorkInfoDto;
import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import com.example.slack.repository.UserRepository;
import com.example.slack.repository.WorkspacesRepository;
import com.example.slack.service.ValidateChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final WorkspacesRepository workspacesRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, WorkspacesRepository workspacesRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.workspacesRepository = workspacesRepository;
    }

    public void registerUser(SignupRequestDto requestDto) {

        ValidateChecker.registerValidCheck(requestDto);

        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        String nickname = requestDto.getNickname();
        Optional<User> found1 = userRepository.findByNickname(nickname);
        if (found1.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 닉네임이 존재합니다.");
        }

        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password, nickname);
        userRepository.save(user);
    }

    public List<WorkInfoDto> getWorkInfo(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        List<Workspaces> workspacesList = workspacesRepository.findByUser(user);
        List<WorkInfoDto> workInfoDtos = new ArrayList<>();
        for (Workspaces workspace : workspacesList) {
            Long workId = workspace.getWorkId();
            String workName = workspace.getWorkName();

            WorkInfoDto workInfoDto = new WorkInfoDto(workId, workName);
            workInfoDtos.add(workInfoDto);
        }

        return workInfoDtos;

    }
}

