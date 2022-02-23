package com.example.slack.service;

import com.example.slack.dto.BookmarkRequestDto;
import com.example.slack.model.Bookmark;
import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import com.example.slack.repository.BookmarkRepository;
import com.example.slack.repository.WorkspacesRepository;
import com.example.slack.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final WorkspacesRepository workspacesRepository;

    //북마크 생성
    @Transactional
    public Bookmark createbookmark(
            BookmarkRequestDto bookmarkRequestDto, User user, Long workId){

        Workspaces workspaces = workspacesRepository.findById(workId).orElseThrow(
                () -> new IllegalArgumentException("워크스페이스가 없습니다.")
        );

        String bookmarkName = bookmarkRequestDto.getBookmarkName();
        String bookmarkUrl = bookmarkRequestDto.getBookmarkUrl();
        if(bookmarkName == null){
            throw new IllegalArgumentException("이름을 입력해주세요.");
        }
        if(bookmarkUrl == null){
            throw new IllegalArgumentException("Url을 입력해주세요.");
        }

        Bookmark bookmark = new Bookmark(bookmarkRequestDto,user, workspaces);
        bookmarkRepository.save(bookmark);
        return bookmark;
    }


    //북마크 삭제
    @Transactional
    public Long deleteBm(Long bookmarkId, UserDetailsImpl userDetails){
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId).orElseThrow(
                () ->new IllegalArgumentException("북마크가 존재하지 않습니다.")
        );

        User user = bookmark.getUser();
        Long deleteId = user.getUserid();
        if(!Objects.equals(userDetails.getUser().getUserid(),deleteId)){
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        bookmarkRepository.deleteById(bookmarkId);
        return bookmarkId;
    }
}