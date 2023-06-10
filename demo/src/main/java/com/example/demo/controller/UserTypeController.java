package com.example.demo.controller;

import com.example.demo.entity.UserType;
import com.example.demo.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/userType")
public class UserTypeController {
    private final UserTypeService userTypeService;

    @Autowired
    public UserTypeController(UserTypeService userTypeService){
        this.userTypeService = userTypeService;
    }

    @GetMapping
    public List<UserType> getUserType(){
        return userTypeService.getUserType();
    }

    @GetMapping("/{userTypeId}")
    public Optional<UserType> getUserType(@PathVariable("userTypeId") Long userTypeId){
        return userTypeService.getUserType(userTypeId);
    }

    @PostMapping
    public ResponseEntity<Object> registerUserType(@RequestBody UserType userType){
        return this.userTypeService.newUserType(userType);
    }

    @PutMapping
    public ResponseEntity<Object> updateUserType(@RequestBody UserType userType){
        return this.userTypeService.newUserType(userType);
    }

    @DeleteMapping("{userTypeId}")
    public ResponseEntity<Object> deleteUserType(@PathVariable("userTypeId") Long userTypeId){
        return this.userTypeService.deleteUserType(userTypeId);
    }
}
