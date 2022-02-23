package com.example.slack.model;

import com.example.slack.dto.BookmarkRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookmarkId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private String bookmarkName;

    @Column(nullable = false)
    private String bookmarkUrl;

    @ManyToOne
    @JoinColumn
    private Workspaces workspaces;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Builder
    public Bookmark(BookmarkRequestDto bookmarkRequestDto,User user, Workspaces workspaces) {
        this.bookmarkName = bookmarkRequestDto.getBookmarkName();
        this.bookmarkUrl = bookmarkRequestDto.getBookmarkUrl();
        this.user = user;
        this.workspaces = workspaces;
    }
}