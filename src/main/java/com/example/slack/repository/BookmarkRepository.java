package com.example.slack.repository;

import com.example.slack.model.Bookmark;
import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
//    List<Bookmark> findByUserAndWorkspaces(User user, Workspaces workspaces);

    List<Bookmark> findAllByOrderByCreatedAtDesc();
}