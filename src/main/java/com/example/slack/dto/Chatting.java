package com.example.slack.dto;

import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chatting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String message;

    @ManyToOne
    @JoinColumn(name = "senderId")
    private User senderId;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    private User receiverId;

    @ManyToOne
    @JoinColumn
    private Workspaces workspacesId;
}
