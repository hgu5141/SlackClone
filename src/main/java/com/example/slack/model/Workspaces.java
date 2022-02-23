package com.example.slack.model;

import com.example.slack.dto.WorkspacesRequestDto;
import com.example.slack.timestamped.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Workspaces extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private String workName;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Builder
    public Workspaces(WorkspacesRequestDto workspacesRequestDto,User user) {
        this.user = user;
        this.workId = workspacesRequestDto.getWorkId();
        this.workName = workspacesRequestDto.getWorkName();

    }
}
