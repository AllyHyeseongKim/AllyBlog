package com.allyhyeseongkim.blog.web.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostsUpdateRequest {

    private String title;
    private String content;

    @Builder
    public PostsUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
