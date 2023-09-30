package com.allyhyeseongkim.blog.service;

import com.allyhyeseongkim.blog.domain.posts.Posts;
import com.allyhyeseongkim.blog.domain.posts.PostsRepository;
import com.allyhyeseongkim.blog.web.dto.PostsResponse;
import com.allyhyeseongkim.blog.web.dto.PostsSaveRequest;
import com.allyhyeseongkim.blog.web.dto.PostsUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequest request) {
        return postsRepository.save(request.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequest request) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post of id " + id +  " not found."));
        posts.update(request.getTitle(), request.getContent());

        return id;
    }

    public PostsResponse findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post of id " + id + " not found."));

        return new PostsResponse(posts);
    }
}
