package com.example.demo.controller;

import com.example.demo.entity.Dispatch;
import com.example.demo.entity.Medicine;
import com.example.demo.entity.User;
import com.example.demo.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/dispatch")
public class DispatchController {
    private final DispatchService dispatchService;

    @Autowired
    public DispatchController(DispatchService dispatchService){
        this.dispatchService = dispatchService;
    }

    @GetMapping
    public List<Dispatch> getDispatch(){
        return dispatchService.getDispatch();
    }

    @GetMapping("/{dispatchId}")
    public Optional<Dispatch> getDispatch(@PathVariable("dispatchId") Long dispatchId){
        return dispatchService.getDispatch(dispatchId);
    }

    @PostMapping
    public ResponseEntity<Object> registerDispatch(@RequestBody Dispatch dispatch){
        //medicine
        Long medicineId = dispatch.getMedicine().getMedicineId();
        Medicine medicine = new Medicine();
        medicine.setMedicineId(medicineId);
        dispatch.setMedicine(medicine);
        //user1
        Long user1Id = dispatch.getUser1().getUserId();
        User user1 = new User();
        user1.setUserId(user1Id);
        dispatch.setUser1(user1);
        //user2
        Long user2Id = dispatch.getUser2().getUserId();
        User user2 = new User();
        user2.setUserId(user2Id);
        dispatch.setUser2(user2);

        return this.dispatchService.newDispatch(dispatch);
    }

    @PutMapping
    public ResponseEntity<Object> updateDispatch(@RequestBody Dispatch dispatch){
        return this.dispatchService.newDispatch(dispatch);
    }

    @DeleteMapping("{dispatchId}")
    public ResponseEntity<Object> deleteDispatch(@PathVariable("dispatchId") Long dispatchId){
        return this.dispatchService.deleteDispatch(dispatchId);
    }
}
