package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String index(Model model) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("First post", "Hello! Today is Monday."));
        posts.add(new Post("Second post", "Tomorrow is Tuesday and it's going to rain."));
        model.addAttribute("posts", posts);
        model.addAttribute("noPostsFound", posts.size() == 0); //this is optional, just to make sure to have this attribute in case it needs to be used in a logic in the view.
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model) {
        Post post = new Post("Wednesday, June 10, 2020", "Today we had our first half-day Wednesday and I managed to get some homework as well as house chores done.");
        model.addAttribute("post", post);
        model.addAttribute("postId", id); //this is optional, just to have this attribute in case it needs to be used in the view at some point.
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String showForm() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String save() {
        return "create a new post";
    }

}
