package com.example.demo.controller;

import com.example.demo.entity.UserPlan;
import com.example.demo.service.UserPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/userPlan")
public class UserPlanController {
    private final UserPlanService userPlanService;

    @Autowired
    public UserPlanController(UserPlanService userPlanService){ this.userPlanService = userPlanService; }

    @GetMapping
    public List<UserPlan> getUserPlan() { return userPlanService.getUserPlan();}

    @GetMapping("/{userPlanId}")
    public Optional<UserPlan> getUserPlan(@PathVariable("userPlanId") Long userPlanId){
        return userPlanService.getUserPlan(userPlanId);
    }

    @PostMapping
    public ResponseEntity<Object> registerUserPlan(@RequestBody UserPlan userPlan){
        return this.userPlanService.newUserPlan(userPlan);
    }

    @PutMapping
    public ResponseEntity<Object> updateUserPlan(@RequestBody UserPlan userPlan){
        return this.userPlanService.newUserPlan(userPlan);
    }

    @DeleteMapping("{userPlanId}")
    public ResponseEntity<Object> deleteUserPlan(@PathVariable("userPlanId") Long userPlanId){
        return this.userPlanService.deleteUserPlan(userPlanId);
    }
}
