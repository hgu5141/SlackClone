package com.example.slack.controller;

import com.example.slack.dto.BookmarkRequestDto;
import com.example.slack.dto.BookmarkResponseDto;
import com.example.slack.model.Bookmark;
import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import com.example.slack.repository.BookmarkRepository;
import com.example.slack.security.UserDetailsImpl;
import com.example.slack.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookmarkController {

    private final BookmarkRepository bookmarkRepository;
    private final BookmarkService bookmarkService;

    //북마크 생성
    @PostMapping("/api/bookmark/{workId}")
    public Bookmark bookmark(
            @RequestBody BookmarkRequestDto bookmarkRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long workId) {

        User user = userDetails.getUser();

        return bookmarkService.createbookmark(bookmarkRequestDto, user, workId);
    }

    //북마크 조회
    @GetMapping("/api/bookmark/{workId}")
    public List<BookmarkResponseDto> getBookmark(@PathVariable Long workId) {
        List<Bookmark> bookmarkList = bookmarkRepository.findAllByOrderByCreatedAtDesc();
        List<BookmarkResponseDto> bookmarkResponseDtos = new ArrayList<>();

        for (Bookmark bookmark : bookmarkList) {
            BookmarkResponseDto bookmarkResponseDto = new BookmarkResponseDto(
                    bookmark.getBookmarkId(),
                    bookmark.getBookmarkName(),
                    bookmark.getBookmarkUrl(),
                    bookmark.getUser(),
                    workId
            );

            bookmarkResponseDtos.add(bookmarkResponseDto);
        }
        return bookmarkResponseDtos;
    }


    //북마크 삭제
    @DeleteMapping("/api/bookmark/{bookmarkId}")
    public Long deleteBm(@PathVariable Long bookmarkId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        bookmarkService.deleteBm(bookmarkId, userDetails);
        return bookmarkId;
    }
}
