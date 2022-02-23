package com.example.slack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkspacesResponseDto {

    private final Long workId;
    private final String workName;
    private final String username;
    private final String nickname;

    public WorkspacesResponseDto(Long workId, String workName, String username, String nickname) {
        this.workId = workId;
        this.workName = workName;
        this.username = username;
        this.nickname = nickname;
    }
}
