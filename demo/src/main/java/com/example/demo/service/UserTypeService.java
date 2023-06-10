package com.example.demo.service;

import com.example.demo.entity.UserType;
import com.example.demo.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserTypeService {
    HashMap<String, Object> userTypeData;
    private final UserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeService(UserTypeRepository userTypeRepository){
        this.userTypeRepository = userTypeRepository;
    }
    public List<UserType> getUserType(){ return this.userTypeRepository.findAll(); }

    public Optional<UserType> getUserType(Long userTypeId){ return this.userTypeRepository.findById(userTypeId); }

    public ResponseEntity<Object> newUserType(UserType userType){
        Optional<UserType> ust = userTypeRepository.findByTypeName(userType.getTypeName());
        userTypeData = new HashMap<>();

        if (ust.isPresent() && userType.getUserTypeId() == null){
            userTypeData.put("Error", true);
            userTypeData.put("message", "One user type already exist");
            return new ResponseEntity<>(
                    userTypeData,
                    HttpStatus.CONFLICT
            );
        }
        userTypeData.put("message", "Save");
        if (userType.getUserTypeId() != null){
            userTypeData.put("message", "Update");
        }
        userTypeRepository.save(userType);
        userTypeData.put("userTypeData", userType);
        userTypeData.put("messasge", "Save");
        return new ResponseEntity<>(
                userTypeData,
                HttpStatus.CREATED
        );
    }

    public  ResponseEntity<Object> deleteUserType(Long userTypeId){
        userTypeData = new HashMap<>();
        boolean exists = this.userTypeRepository.existsById(userTypeId);
        if (!exists){
            userTypeData.put("Error", true);
            userTypeData.put("message", "User Type with specified ID does not exist");
            return new ResponseEntity<>(
                    userTypeData,
                    HttpStatus.CONFLICT
            );
        }
        userTypeRepository.deleteById(userTypeId);
        userTypeData.put("message", "User Type Eliminated");
        return new ResponseEntity<>(
                userTypeData,
                HttpStatus.ACCEPTED
        );
    }
}
