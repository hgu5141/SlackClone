package com.example.slack.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembersResponseDto {

    private final Long workId;
    private final String workName;
    private final Long memberId;
    private final String memberName;

    public MembersResponseDto(Long workId, String workName, Long memberId, String memberName) {
        this.workId = workId;
        this. workName = workName;
        this.memberId = memberId;
        this.memberName = memberName;
    }
}
