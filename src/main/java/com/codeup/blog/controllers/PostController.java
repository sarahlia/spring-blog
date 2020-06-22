package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String postsIndex() {
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postsView(@PathVariable long id) {
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewPostsCreate() {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String postsCreate() {
        return "posts/create";
    }

}
