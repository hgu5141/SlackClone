package com.example.slack.repository;

import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkspacesRepository extends JpaRepository<Workspaces, Long> {
    List<Workspaces> findAllByOrderByCreatedAtDesc();
}