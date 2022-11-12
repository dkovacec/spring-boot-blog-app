package com.brightstraining.springbootblogapplication.controller;

import com.brightstraining.springbootblogapplication.model.Post;
import com.brightstraining.springbootblogapplication.model.UserAccount;
import com.brightstraining.springbootblogapplication.service.PostService;
import com.brightstraining.springbootblogapplication.service.UserAccountService;
import com.brightstraining.springbootblogapplication.service.UserAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserAccountService userAccountService;
    //private UserAccountServiceImpl userAccountServiceImpl;

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

    //hardcoding one userAccount to connect with posts
    @GetMapping("/posts/newpost")
    public String createNewPost(Model model) {
        Optional<UserAccount> optionalUserAccount = userAccountService.findOneByEmail("jredders@gmail.com");
        if (optionalUserAccount.isPresent()) {
            Post post = new Post();
            post.setUserAccount(optionalUserAccount.get());
            model.addAttribute("post", post);
            return "newPost";
        } else {

            return "notfound";
        }
    }




    @PostMapping("/posts/newpost")
    public String saveNewPost(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/posts/" + post.getId();   //after saving go to that post url
    }



}
