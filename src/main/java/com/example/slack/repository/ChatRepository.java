package com.example.slack.repository;

import com.example.slack.dto.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Long, Chatting> {

}
