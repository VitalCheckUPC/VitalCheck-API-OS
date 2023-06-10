package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserPlan;
import com.example.demo.entity.UserType;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService; }

    @GetMapping
    public List<User> getUser(){ return userService.getUser(); }

    @GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Long userId){ return userService.getUser(userId); }

    @PostMapping
    public ResponseEntity<Object> registerUser(@RequestBody User user){
        Long userTypeId = user.getUserType().getUserTypeId();
        UserType userType = new UserType();
        userType.setUserTypeId(userTypeId);
        user.setUserType(userType);

        Long userPlanId = user.getUserPlan().getUserPlanId();
        UserPlan userPlan = new UserPlan();
        userPlan.setUserPlanId(userPlanId);
        user.setUserPlan(userPlan);

        if(user.getRuc() != null && String.valueOf(user.getRuc()).length() > 11){
            return ResponseEntity.badRequest().body("The RUC must have only 11 digits");
        }
        return this.userService.newUser(user);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        return this.userService.newUser(user);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Long userId){
        return this.userService.deleteUser(userId);
    }

}
