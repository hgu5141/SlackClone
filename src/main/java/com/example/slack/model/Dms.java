package com.example.slack.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Entity
@NoArgsConstructor
@Getter
public class Dms {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long dmsId;

    @ManyToOne
    @JoinColumn(name = "receiver_Id")
    private Members members;

    @ManyToOne
    @JoinColumn(name = "sender_Id")
    private User user;

    @ManyToOne
    @JoinColumn
    private Workspaces workspaces;

    @Column
    private String message;

    public Dms(Members members, User user, Workspaces workspaces, String message) {
        this.members = members;
        this.user = user;
        this.workspaces = workspaces;
        this.message = message;
    }

    public Dms(Optional<Members> members, User user, Optional<Workspaces> workspaces, String message) {
    }
}
