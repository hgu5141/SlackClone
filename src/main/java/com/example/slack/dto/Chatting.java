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


//userimpl에서 가져온다
    public Chatting(String message, User senderId, User receiverId, Workspaces workspacesId) {
        this.message = message;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.workspacesId = workspacesId;
    }

    //db에서 userid검색해야함
    public Chatting(Message message) {
        this.message = message.getMessage();
    }
}
