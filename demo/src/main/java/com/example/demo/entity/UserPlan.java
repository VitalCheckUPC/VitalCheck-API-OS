package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "UserPlan")
public class UserPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPlanId;
    private String planName;

    @OneToMany(mappedBy = "userPlan")
    @JsonBackReference
    private List<User> user;

    public UserPlan() {
    }

    public UserPlan(Long userPlanId){
        this.userPlanId = userPlanId;
    }

    public UserPlan(Long userPlanId, String planName) {
        this.userPlanId = userPlanId;
        this.planName = planName;
    }

    public UserPlan(String planName) {
        this.planName = planName;
    }


    public Long getUserPlanId() {
        return userPlanId;
    }

    public void setUserPlanId(Long userPlanId) {
        this.userPlanId = userPlanId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String PlanName) {
        this.planName = PlanName;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

}
