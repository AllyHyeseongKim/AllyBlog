package com.allyhyeseongkim.blog.web;

import com.allyhyeseongkim.blog.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        return "blog";
    }

    @GetMapping("/blog/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postsService.findById(id));

        return "blog/single-post";
    }

    @GetMapping("/blog/posts/{id}/update")
    public String updatePost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postsService.findById(id));

        return "blog/update-posts";
    }

    @GetMapping("/blog/posts/save")
    public String postsSave() {
        return "blog/save-posts";
    }
}
