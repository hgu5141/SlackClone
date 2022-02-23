package com.example.slack.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

@Getter
public class BookmarkRequestDto {

    private String bookmarkName;

    @NotNull
    @URL
    private String bookmarkUrl;

    private Long workId;

}