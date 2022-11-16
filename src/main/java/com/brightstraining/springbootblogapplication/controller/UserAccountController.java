package com.brightstraining.springbootblogapplication.controller;

import com.brightstraining.springbootblogapplication.model.Authority;
import com.brightstraining.springbootblogapplication.model.UserAccount;
import com.brightstraining.springbootblogapplication.repository.AuthorityRepository;
import com.brightstraining.springbootblogapplication.repository.UserAccountRepository;
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

    private UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountController(UserAccountService userAccountService,
                                 AuthorityRepository authorityRepository,
                                 UserAccountRepository userAccountRepository) {
        this.userAccountService = userAccountService;
        this.authorityRepository = authorityRepository;
        this.userAccountRepository = userAccountRepository;

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


//    @PostMapping("/posts/showFormForUpdate/id")
//    public String userAccountUpdate(@Valid @ModelAttribute UserAccount userAccount,
//                                    BindingResult bindingUser) {
////        if(bindingUser.hasErrors()){
////            return "updateUser";
////        }
//        try {
////            if (userAccount.getAuthorities().equals("ROLE_USER")) {
////                Set<Authority> authorities = new HashSet<>();
////                authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities::add);
////                userAccount.setAuthorities(authorities);
////            }
//            this.userAccountRepository.save(userAccount);
////            this.userAccountService.saveUser(userAccount);
//        } catch (Exception e) {
//            return "updateUser";
//        }
//        return "redirect:userList";
//    }


//    @PostMapping("/posts/register")
    @PostMapping("/posts/updateUser/")
    public String userAccountUpdate(@Valid @ModelAttribute UserAccount userAccount,
                                  BindingResult bindingResult, Model model,
                                    @RequestParam Long id) {
//        if(bindingResult.hasErrors()) {
//            return "updateUser";
//        }

        this.userAccountService.saveUser(userAccount);
//        return "redirect:/posts/";  //return to homepage after update
        return "redirect:/posts/useraccounts";  //return to userlist after registering
    }


}