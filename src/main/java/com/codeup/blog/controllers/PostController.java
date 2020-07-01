package com.codeup.blog.controllers;

import com.codeup.blog.daos.PostsRepository;
import com.codeup.blog.daos.UsersRepository;
import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private PostsRepository postsDao;
    private UsersRepository usersDao;
    private final EmailService emailService;

    public PostController(PostsRepository postsRepository, UsersRepository usersRepository, EmailService emailService) {
        this.postsDao = postsRepository;
        this.usersDao = usersRepository;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String index(Model model) {
//        ArrayList<Post> posts = new ArrayList<>();
//        posts.add(new Post("First post", "Hello! Today is Monday."));
//        posts.add(new Post("Second post", "Tomorrow is Tuesday and it's going to rain."));

        //just an example of a query method(findFirstByTitle in this case)
        //Post firstPost = postsDao.findFirstByTitle("30");
        //System.out.println("firstPost.getId() = " + firstPost.getId());

        List<Post> postsList = postsDao.findAll();
        model.addAttribute("posts", postsList);
        model.addAttribute("noPostsFound", postsList.size() == 0); //this is optional, just to make sure to have this attribute in case it needs to be used in a logic in the view.
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model) {
        Post post = postsDao.getOne(id);
        model.addAttribute("post", post);
        model.addAttribute("postId", id); //this is optional, just to have this attribute in case it needs to be used in the view at some point.
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    public String showForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String save(@ModelAttribute Post postToBeSaved) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToBeSaved.setUser(currentUser);
        Post savedPost = postsDao.save(postToBeSaved);
        emailService.prepareAndSend(savedPost, "A new post has been created.", "A new post has been created with the id of " + savedPost.getId());
        return "redirect:/posts/" + savedPost.getId(); //if we just want to redirect it to the /posts page, no need to concatenate with savedPost.getId()
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(Model model, @PathVariable long id) {
        //find a post
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@ModelAttribute Post postToEdit) {
        User currentUser = usersDao.getOne(1L);
        postToEdit.setUser(currentUser);
        postsDao.save(postToEdit);
        return "redirect:/posts/" + postToEdit.getId();
    }

    @PostMapping("/posts/{id}/delete")
    @ResponseBody
    public String destroy(@PathVariable long id) {
        postsDao.deleteById(id);
        return "post deleted.";
    }

    @GetMapping("/search")
    public String searchResults(Model model, @RequestParam(name = "term") String term) {
        List<Post> posts = postsDao.searchByTitleOrBody(term);
        model.addAttribute("posts", posts);
        return "posts/index";
    }


}
