package com.brightstraining.springbootblogapplication.controller;

import com.brightstraining.springbootblogapplication.model.Post;
import com.brightstraining.springbootblogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts/{id}")   //dynamic url to see single post
    public String getPost(@PathVariable Long id, Model model) {
        //search post by id
        Optional<Post> optionalPost = Optional.ofNullable(postService.getPostById(id));
        //if post exists
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);

            return "post";  //return the post to the front
        }
        return "notfound";  //case if post does not exist
    }


}
