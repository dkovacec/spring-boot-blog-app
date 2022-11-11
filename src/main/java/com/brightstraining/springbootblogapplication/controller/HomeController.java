package com.brightstraining.springbootblogapplication.controller;

import com.brightstraining.springbootblogapplication.model.Post;
import com.brightstraining.springbootblogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @GetMapping("/")  //home page, will show  all posts
    public String home(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "index";
    }
}
