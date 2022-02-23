package com.example.slack.dto;

import com.example.slack.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkResponseDto {

    private final Long bookmarkId;
    private final String bookmarkName;
    private final String bookmarkUrl;
    private final User user;
    private final Long workId;

    public BookmarkResponseDto(Long bookmarkId, String bookmarkName, String bookmarkUrl, User user, Long workId) {
        this.bookmarkId = bookmarkId;
        this.bookmarkName = bookmarkName;
        this.bookmarkUrl = bookmarkUrl;
        this.user = user;
        this.workId = workId;
    }
}