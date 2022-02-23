package com.example.slakclone.model;

import com.example.slakclone.dto.WorkspacesRequestDto;
import com.example.slakclone.timestamped.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Workspaces extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
