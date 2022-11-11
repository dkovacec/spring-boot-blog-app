package com.brightstraining.springbootblogapplication.service;

import com.brightstraining.springbootblogapplication.model.User;
import com.brightstraining.springbootblogapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User getUser(long id) {
        //creating this in case there is no value for id field
        Optional<User> optional = this.userRepository.findById(id);
        User user = null;

        if(optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User with that id " + id + " was not found");
        }
        return user;
    }

    @Override
    public void deleteUserById(long id) {
        // add exception here for checking if incorrect id is entered
        boolean exists = this.userRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("User with that id " + id + " was not found.");
        }
        //delete employee if exists
        this.userRepository.deleteById(id);

    }
}
