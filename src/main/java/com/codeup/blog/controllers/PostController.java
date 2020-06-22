package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postsIndex() {
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postsView(@PathVariable long id) {
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewPostsCreate() {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postsCreate() {
        return "posts/create";
    }

}
