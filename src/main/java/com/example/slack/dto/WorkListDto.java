package com.example.slack.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkListDto {
    String workName;
    Long workId;

    public WorkListDto(Long workId, String workName) {
        this.workId = workId;
        this.workName = workName;
    }
}
