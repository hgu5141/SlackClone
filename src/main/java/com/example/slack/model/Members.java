package com.example.slack.model;

import com.example.slack.dto.MembersRequestDto;
import com.example.slack.timestamped.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class Members extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    private String memberName;

    @ManyToOne
    @JoinColumn(name = "workId")
    private Workspaces workspaces;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;


    @Builder
    public Members(MembersRequestDto membersRequestDto, Workspaces workspaces, User user) {
        this.memberName = membersRequestDto.getMemberName();
        this.user = user;
        this.workspaces = workspaces;
    }
}