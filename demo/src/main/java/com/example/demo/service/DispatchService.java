package com.example.demo.service;

import com.example.demo.entity.Dispatch;
import com.example.demo.repository.DispatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class DispatchService {
    HashMap<String, Object> dispatchData;
    private final DispatchRepository dispatchRepository;

    @Autowired
    public DispatchService(DispatchRepository dispatchRepository){
        this.dispatchRepository = dispatchRepository;
    }

    public List<Dispatch> getDispatch(){
        return this.dispatchRepository.findAll();
    }

    public Optional<Dispatch> getDispatch(Long dispatchId){
        return this.dispatchRepository.findById(dispatchId);
    }

    public ResponseEntity<Object> newDispatch(Dispatch dispatch){
        Optional<Dispatch> id = dispatchRepository.findByDispatchId(dispatch.getDispatchId());
        dispatchData = new HashMap<>();

        if (id.isPresent() && dispatch.getDispatchId() == null){
            dispatchData.put("Error", true);
            dispatchData.put("message", "One dispatch already exist");
            return new ResponseEntity<>(
                    dispatchData,
                    HttpStatus.CONFLICT
            );
        }
        dispatchData.put("message", "Save");
        if(dispatch.getDispatchId() != null){
            dispatchData.put("message", "Update");
        }
        dispatchRepository.save(dispatch);
        dispatchData.put("dispatchData", dispatch);
        dispatchData.put("message", "Save");
        return new ResponseEntity<>(
                dispatchData,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteDispatch(Long dispatchId){
        dispatchData = new HashMap<>();
        boolean exists = this.dispatchRepository.existsById(dispatchId);
        if(!exists){
            dispatchData.put("Error", true);
            dispatchData.put("message", "Dispatch with specified ID does not exist");
            return new ResponseEntity<>(
                    dispatchData,
                    HttpStatus.CONFLICT
            );
        }
        dispatchRepository.deleteById(dispatchId);
        dispatchData.put("message", "Dispatch Eliminated");
        return new ResponseEntity<>(
                dispatchData,
                HttpStatus.ACCEPTED
        );
    }
}
