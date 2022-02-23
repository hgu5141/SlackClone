package com.example.slakclone.repository;


import com.example.slakclone.model.User;
import com.example.slakclone.model.Workspaces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkspacesRepository extends JpaRepository<Workspaces, Long> {
    List<Workspaces> findAllByOrderByCreatedAtDesc();
//    List<Workspaces> findByUsername(String username);
    List<Workspaces> findByUser(Optional<User> user);
}