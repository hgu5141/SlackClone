package com.example.slack.repository;

import com.example.slack.model.Members;
import com.example.slack.model.Workspaces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//<>타입 설정안해주면 서비스에서 save 쓸 때 타입에러
public interface MembersRepository extends JpaRepository<Members, Long> {
    List<Members> findByWorkspaces(Optional<Workspaces> workspaces);
//    Optional<Members> findById(Long workId, Long memberId);
    Optional<Members> findByMemberNameAndWorkspaces(String memberName, Workspaces workspaces);
//    String getMemberName();

}