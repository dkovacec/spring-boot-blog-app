package com.brightstraining.springbootblogapplication.service;

import com.brightstraining.springbootblogapplication.model.UserAccount;

import java.util.List;

public interface UserService {
    List<UserAccount> getAllUsers();
    void saveUser(UserAccount userAccount);
    UserAccount getUser(long id);
    void deleteUserById(long id);

}
