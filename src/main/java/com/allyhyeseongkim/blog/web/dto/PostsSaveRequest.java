package com.allyhyeseongkim.blog.web.dto;

import com.allyhyeseongkim.blog.domain.posts.Posts;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostsSaveRequest {

    private String title;
    private String content;

    @Builder
    public PostsSaveRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .build();
    }
}
