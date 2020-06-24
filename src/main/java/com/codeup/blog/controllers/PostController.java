package com.codeup.blog.controllers;

import com.codeup.blog.daos.PostsRepository;
import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private PostsRepository postsDao;

    public PostController(PostsRepository postsRepository) {
        postsDao = postsRepository;
    }

    @GetMapping("/posts")
    public String index(Model model) {
//        ArrayList<Post> posts = new ArrayList<>();
//        posts.add(new Post("First post", "Hello! Today is Monday."));
//        posts.add(new Post("Second post", "Tomorrow is Tuesday and it's going to rain."));
        List<Post> postsList = postsDao.findAll();
        model.addAttribute("posts", postsList);
        model.addAttribute("noPostsFound", postsList.size() == 0); //this is optional, just to make sure to have this attribute in case it needs to be used in a logic in the view.
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
        Post newPost = new Post("Tuesday, June 23, 2020", "We ended class an hour early today.");
        postsDao.save(newPost);
        return "create a new post";
    }

    @GetMapping("/ads/{id}/edit")
    public String showEditForm(Model model, @PathVariable long id) {
        //find a post
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    @ResponseBody
    public String update(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {

        //find a post (select * from posts where id = ?)
        Post foundPost = postsDao.getOne(id);

        //edit the post
        foundPost.setTitle(title);
        foundPost.setBody(body);

        //save the changes (update posts set title = ?, body = ? where id = ?)
        postsDao.save(foundPost);
        return "post updated.";
    }

    @DeleteMapping("/posts/{id}/delete")
    @ResponseBody
    public String destroy(@PathVariable long id) {
        postsDao.deleteById(id);
        return "post deleted.";
    }


}
