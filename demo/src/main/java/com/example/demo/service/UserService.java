package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    HashMap<String, Object> userData;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){ this.userRepository = userRepository; }

    public List<User> getUser(){ return this.userRepository.findAll(); }

    public Optional<User> getUser(Long userId){ return this.userRepository.findById(userId); }

    public ResponseEntity<Object> newUser(User user){
        Optional<User> us = userRepository.findByUserName(user.getUserName());
        userData = new HashMap<>();

        if(us.isPresent() && user.getUserId() == null){
            userData.put("Error", true);
            userData.put("message", "One user already exist");
            return new ResponseEntity<>(
                    userData,
                    HttpStatus.CONFLICT
            );
        }
        userData.put("message", "Save");
        if (user.getUserId() != null){
            userData.put("message", "Update");
        }
        userRepository.save(user);
        userData.put("userData", user);
        userData.put("message", "Save");
        return new ResponseEntity<>(
                userData,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteUser(Long userId){
        userData = new HashMap<>();
        boolean exists = this.userRepository.existsById(userId);
        if(!exists){
            userData.put("Error", true);
            userData.put("message", "User with specified ID does not exist");
            return new ResponseEntity<>(
                    userData,
                    HttpStatus.CONFLICT
            );
        }
        userRepository.deleteById(userId);
        userData.put("message", "User Eliminated");
        return new ResponseEntity<>(
                userData,
                HttpStatus.ACCEPTED
        );
    }

}
