package com.brightstraining.springbootblogapplication.controller;

import com.brightstraining.springbootblogapplication.model.Comment;
import com.brightstraining.springbootblogapplication.model.Post;
import com.brightstraining.springbootblogapplication.model.UserAccount;
import com.brightstraining.springbootblogapplication.service.CommentService;
import com.brightstraining.springbootblogapplication.service.PostService;
import com.brightstraining.springbootblogapplication.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes("comment")
public class CommentController {

    private PostService postService ;
    private UserAccountService userAccountService ;
    private CommentService commentService ;

    @Autowired
    public CommentController(PostService postService, UserAccountService userAccountService, CommentService commentService) {
        this.postService = postService;
        this.userAccountService = userAccountService ;
        this.commentService = commentService ;
    }

    @GetMapping("/posts/{id}/comment")
    public String showComment(@PathVariable Long id, Model model ) {
        String authUserName = "authUserName";

        Optional<UserAccount> optionalUserAccount = this.userAccountService.findOneByEmail(authUserName);

        Optional<Post> postOptional = Optional.ofNullable(this.postService.getPostById(id));

        if(postOptional.isPresent() && optionalUserAccount.isPresent()) {
            Comment comment = new Comment();
            comment.setPost(postOptional.get());
            comment.setUser(optionalUserAccount.get());
            model.addAttribute("comment", comment);
            return "commentForm";
        } else {
            return "notfound";
        }
    }

    @PostMapping("/posts/{id}/comment")
    @PreAuthorize("isAuthenticated()")
    public String saveNewComment (@Valid @ModelAttribute Comment comment,
                                  BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "commentForm";
        } else {
            commentService.save(comment);
            return "redirect:/posts/{id}" + comment.getPost().getId();
        }
    }

}
