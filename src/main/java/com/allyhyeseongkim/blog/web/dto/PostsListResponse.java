package com.allyhyeseongkim.blog.web.dto;

import com.allyhyeseongkim.blog.domain.posts.Posts;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostsListResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDate modifiedDate;

    public PostsListResponse(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate().toLocalDate();

        if (entity.getContent().length() > 50) {
            this.content = entity.getContent().substring(0, 50) + "...";
        }
    }
}
