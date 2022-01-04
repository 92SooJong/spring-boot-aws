package com.soojong.springboot.web.dto;

import com.soojong.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsUpdateRequestDto {

    private String title;
    private String content;

    @Builder

    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
