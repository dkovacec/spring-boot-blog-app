package com.brightstraining.springbootblogapplication.controller;

import com.brightstraining.springbootblogapplication.model.Authority;
import com.brightstraining.springbootblogapplication.model.UserAccount;
import com.brightstraining.springbootblogapplication.repository.AuthorityRepository;
import com.brightstraining.springbootblogapplication.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(method={RequestMethod.POST,RequestMethod.GET})
public class UserAccountController {

    private UserAccountService userAccountService;

    private AuthorityRepository authorityRepository;

    @Autowired
    public UserAccountController(UserAccountService userAccountService,
                                 AuthorityRepository authorityRepository) {
        this.userAccountService = userAccountService;
        this.authorityRepository = authorityRepository;
    }

    @GetMapping("/posts/useraccounts")
    public String userAccountsList(Model model) {
        model.addAttribute("listOfUserAccounts", userAccountService.getAllUsers());

        return "userList";
    }

    @GetMapping("/posts/showFormForUpdate/{id}")
    public String userAccountUpdateForm(@PathVariable(value = "id") Long id, Model model) {
        //model.addAttribute("userAccount", userAccountService.getUser(id));
        UserAccount userAccount = userAccountService.getUser(id);
        model.addAttribute("userAccount", userAccount);
        return "updateUser";
    }


    @PostMapping("/posts/showFormForUpdate/id")

    public String userAccountUpdate(@Valid @ModelAttribute UserAccount userAccount,
                                    BindingResult bindingUser) {
//        if(bindingUser.hasErrors()){
//            return "updateUser";
//        }
        try {
//            if (userAccount.getAuthorities().equals("ROLE_USER")) {
//                Set<Authority> authorities = new HashSet<>();
//                authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities::add);
//                userAccount.setAuthorities(authorities);
//            }
            userAccountService.saveUser(userAccount);
        } catch (Exception e) {
            return "updateUser";
        }
        return "redirect:userList";
    }

}