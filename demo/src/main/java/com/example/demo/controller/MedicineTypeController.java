package com.example.demo.controller;

import com.example.demo.entity.MedicineType;
import com.example.demo.service.MedicineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/medicineType")
public class MedicineTypeController {
    private final MedicineTypeService medicineTypeService;

    @Autowired
    public MedicineTypeController(MedicineTypeService medicineTypeService){
        this.medicineTypeService = medicineTypeService;
    }

    @GetMapping
    public @ResponseBody List<MedicineType> getMedicineType(){
        return medicineTypeService.getMedicineType();
    }

    @GetMapping("/{medicineTypeId}")
    public @ResponseBody Optional<MedicineType> getMedicineType(@PathVariable("medicineTypeId") Long medicineTypeId){
        return medicineTypeService.getMedicineType(medicineTypeId);
    }

    @PostMapping
    public ResponseEntity<Object> registerMedicineType(@RequestBody MedicineType medicineType){
        return this.medicineTypeService.newMedicineType(medicineType);
    }

    @PutMapping
    public ResponseEntity<Object> updateMedicineType(@RequestBody MedicineType medicineType){
        return this.medicineTypeService.newMedicineType(medicineType);
    }

    @DeleteMapping("{medicineTypeId}")
    public ResponseEntity<Object> deleteMedicineType(@PathVariable("medicineTypeId") Long medicineTypeId){
        return  this.medicineTypeService.deleteMedicineType(medicineTypeId);
    }

}
