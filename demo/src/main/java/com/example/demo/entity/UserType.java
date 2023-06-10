package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "UserType")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userTypeId;
    private String typeName;

    @OneToMany(mappedBy = "userType")
    @JsonBackReference
    private List<User> user;
    public UserType() {
    }

    public UserType(Long userTypeId){
        this.userTypeId = userTypeId;
    }

    public UserType(Long userTypeId, String typeName) {
        this.userTypeId = userTypeId;
        this.typeName = typeName;
    }

    public UserType(String typeName) {
        this.typeName = typeName;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
