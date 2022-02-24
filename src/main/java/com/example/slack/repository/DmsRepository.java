package com.example.slack.repository;

import com.example.slack.model.User;
import com.example.slack.model.Dms;
import com.example.slack.model.Members;
import com.example.slack.model.Workspaces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DmsRepository extends JpaRepository<Dms, Long> {
    List<Dms> findByWorkspacesAndUserAndMembers(Workspaces workspaces, User user, Members members);
}
