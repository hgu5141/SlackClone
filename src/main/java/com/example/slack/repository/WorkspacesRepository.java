package com.example.slack.repository;


import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkspacesRepository extends JpaRepository<Workspaces, Long> {
    List<Workspaces> findAllByOrderByCreatedAtDesc();
//    List<Workspaces> findByUsername(String username);
    List<Workspaces> findByUser(User user);
    List<Workspaces> findByUser(Optional<User> user);
}