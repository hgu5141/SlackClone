package com.example.slack.model;

import com.example.slack.timestamped.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Workspaces extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workspace_id;

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String Url;

    @Column
    private LocalDateTime createdAT;

    @Column
    private LocalDateTime updatedAt;
}
