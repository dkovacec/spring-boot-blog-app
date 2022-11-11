package com.brightstraining.springbootblogapplication.service;

import com.brightstraining.springbootblogapplication.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
    User getUser(long id);
    void deleteUserById(long id);

}
