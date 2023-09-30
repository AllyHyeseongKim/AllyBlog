package com.allyhyeseongkim.blog.web;

import com.allyhyeseongkim.blog.domain.posts.Posts;
import com.allyhyeseongkim.blog.domain.posts.PostsRepository;
import com.allyhyeseongkim.blog.web.dto.PostsSaveRequest;
import com.allyhyeseongkim.blog.web.dto.PostsUpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate;
    private final PostsRepository postsRepository;

    @Autowired
    public PostsApiControllerTest(TestRestTemplate restTemplate, PostsRepository postsRepository) {
        this.restTemplate = restTemplate;
        this.postsRepository = postsRepository;
    }

    @AfterEach
    public void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("[PostApiController] save posts")
    public void savePostsTest() {

        //given
        String title = "title";
        String content = "content";

        PostsSaveRequest request = PostsSaveRequest.builder()
                .title(title)
                .content(content)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, request, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> actual = postsRepository.findAll();
        assertThat(actual.get(0).getTitle()).isEqualTo(title);
        assertThat(actual.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("[PostApiController] update posts")
    public void updatePostsTest() {

        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .build()
        );

        Long updateId = savedPosts.getId();
        String expectedTitle = "updated title";
        String expectedContent = "updated content";

        PostsUpdateRequest request = PostsUpdateRequest.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequest> requestEntity = new HttpEntity<>(request);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> actual = postsRepository.findAll();
        assertThat(actual.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(actual.get(0).getContent()).isEqualTo(expectedContent);
    }
}