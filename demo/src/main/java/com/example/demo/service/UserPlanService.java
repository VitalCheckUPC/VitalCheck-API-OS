package com.example.demo.service;

import com.example.demo.entity.UserPlan;
import com.example.demo.repository.UserPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserPlanService {
    HashMap<String, Object> userPlanData;
    private final UserPlanRepository userPlanRepository;

    @Autowired
    public UserPlanService(UserPlanRepository userPlanRepository){ this.userPlanRepository = userPlanRepository; }

    public List<UserPlan> getUserPlan(){ return this.userPlanRepository.findAll(); }

    public Optional<UserPlan> getUserPlan(Long userPlanId){ return this.userPlanRepository.findById(userPlanId); }

    public ResponseEntity<Object> newUserPlan(UserPlan userPlan){
        Optional<UserPlan> usp = userPlanRepository.findByPlanName(userPlan.getPlanName());
        userPlanData = new HashMap<>();

        if (usp.isPresent() && userPlan.getUserPlanId() == null){
            userPlanData.put("Error", true);
            userPlanData.put("message", "One User Plan already exist");
            return new ResponseEntity<>(
                    userPlanData,
                    HttpStatus.CONFLICT
            );
        }
        userPlanData.put("message", "Save");
        if (userPlan.getUserPlanId() != null){
            userPlanData.put("message", "Update");
        }
        userPlanRepository.save(userPlan);
        userPlanData.put("userPlanData", userPlan);
        userPlanData.put("message", "Save");
        return new ResponseEntity<>(
                userPlanData,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteUserPlan(Long userPlanId){
        userPlanData = new HashMap<>();
        boolean exists = this.userPlanRepository.existsById(userPlanId);
        if (!exists){
            userPlanData.put("Error", true);
            userPlanData.put("message", "User Plan with specified ID does not exist");
            return new ResponseEntity<>(
                    userPlanData,
                    HttpStatus.CONFLICT
            );
        }
        userPlanRepository.deleteById(userPlanId);
        userPlanData.put("message", "User Plan Eliminated");
        return new ResponseEntity<>(
                userPlanData,
                HttpStatus.ACCEPTED
        );
    }

}
