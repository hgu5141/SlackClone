package com.example.slack.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WorkspacesRequestDto {
    private Long workId;
    private String workName;
}
