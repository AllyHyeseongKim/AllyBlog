package com.allyhyeseongkim.blog.web;

import com.allyhyeseongkim.blog.service.PostsService;
import com.allyhyeseongkim.blog.web.dto.PostsResponse;
import com.allyhyeseongkim.blog.web.dto.PostsSaveRequest;
import com.allyhyeseongkim.blog.web.dto.PostsUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequest request) {
        return postsService.save(request);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequest request) {
        return postsService.update(id, request);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponse findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
