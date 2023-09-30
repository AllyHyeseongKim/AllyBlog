package com.allyhyeseongkim.blog.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostsRepositoryTest {

    private final PostsRepository postsRepository;

    @Autowired
    public PostsRepositoryTest(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @AfterEach
    public void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("[PostsRepository] find all posts")
    public void findAllPostsTest() {

        //given
        String title = "test title";
        String content = "test content";
        postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                .build()
        );

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("[PostsRepository] save BaseTime")
    public void saveBaseTimeTest() {

        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .build()
        );

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts actual = postsList.get(0);

        assertThat(actual.getCreatedDate()).isAfter(now);
        assertThat(actual.getModifiedDate()).isAfter(now);
    }
}