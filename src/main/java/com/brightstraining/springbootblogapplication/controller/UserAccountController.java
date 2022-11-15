package com.brightstraining.springbootblogapplication.controller;

import com.brightstraining.springbootblogapplication.model.UserAccount;
import com.brightstraining.springbootblogapplication.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserAccountController {

    private UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/posts/useraccounts")
    public String userAccountsList(Model model) {
        model.addAttribute("listOfUserAccounts", userAccountService.getAllUsers());

        return "userList";
    }

    @GetMapping("/posts/useraccounts/{id}")
    public String userAccountUpdateForm(Model model, @PathVariable(value = "id") long id) {
        //model.addAttribute("userAccount", userAccountService.getUser(id));
        UserAccount userAccount = userAccountService.getUser(id);
        model.addAttribute("userAccount", userAccount);

        return "updateUser";
    }


    @PostMapping("/posts/useraccounts/{id}")
    public String userAccountUpdate(@Valid @ModelAttribute UserAccount userAccount,
                                    BindingResult bindingUser) {
        if(bindingUser.hasErrors()){
            return "updateUser";
        }
        try {
            userAccountService.saveUser(userAccount);

        } catch (Exception e) {
            return "updateUser";
        }
        return "redirect: posts/accounts";
    }

}