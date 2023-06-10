package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    @NotNull
    private String userName;
    @Column(nullable = false)
    @NotNull
    private String email;
    @Column(nullable = false)
    @NotNull
    private String password;
    @Column(unique = true, nullable = false)
    @NotNull
    private Long ruc;
    @Column(nullable = false)
    @NotNull
    private LocalDate registrationDate;


    @ManyToOne
    @JoinColumn(name = "userPlanId")
    private UserPlan userPlan;

    @ManyToOne
    @JoinColumn(name = "userTypeId")
    private UserType userType;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Sale> sales;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Inventory> inventories;
    @OneToMany(mappedBy = "user1")
    @JsonIgnore
    private List<Dispatch> dispatches1;

    @OneToMany(mappedBy = "user2")
    @JsonIgnore
    private List<Dispatch> dispatches2;

    //Getters and setters

    public User() {
    }

    public User(Long userId) {
        this.userId = userId;
    }

    public User(Long userId, String userName, String email, String password, Long ruc, LocalDate registrationDate) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.ruc = ruc;
        this.registrationDate = registrationDate;
    }

    public User(String userName, String email, String password, Long ruc, LocalDate registrationDate) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.ruc = ruc;
        this.registrationDate = registrationDate;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRuc() {
        return ruc;
    }

    public void setRuc(Long ruc) {
        this.ruc = ruc;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public UserPlan getUserPlan() {
        return userPlan;
    }

    public void setUserPlan(UserPlan userPlan) {
        this.userPlan = userPlan;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    public List<Dispatch> getDispatches1() {
        return dispatches1;
    }

    public void setDispatches1(List<Dispatch> dispatches1) {
        this.dispatches1 = dispatches1;
    }

    public List<Dispatch> getDispatches2() {
        return dispatches2;
    }

    public void setDispatches2(List<Dispatch> dispatches2) {
        this.dispatches2 = dispatches2;
    }

    public void setUser(User user) {
    }
}
