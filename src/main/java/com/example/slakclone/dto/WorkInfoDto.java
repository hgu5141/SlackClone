package com.example.slakclone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkInfoDto {
    String workName;
    Long workId;

    public WorkInfoDto(Long workId, String workName) {
        this.workId = workId;
        this.workName = workName;
    }
}
