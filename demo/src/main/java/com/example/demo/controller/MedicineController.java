package com.example.demo.controller;

import com.example.demo.entity.Medicine;
import com.example.demo.entity.MedicineType;
import com.example.demo.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/medicine")
public class MedicineController {
    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService){
        this.medicineService = medicineService;
    }

    @GetMapping
    public List<Medicine> getMedicine(){
        return medicineService.getMedicine();
    }

    @GetMapping("/{medicineId}")
    public Optional<Medicine> getMedicine(@PathVariable("medicineId") Long medicineId){
        return medicineService.getMedicine(medicineId);
    }

    @PostMapping
    public ResponseEntity<Object> registerMedicine(@RequestBody Medicine medicine){
        Long medicineTypeId= medicine.getMedicineType().getMedicineTypeId();
        MedicineType medicineType = new MedicineType();
        medicineType.setMedicineTypeId(medicineTypeId);
        medicine.setMedicineType(medicineType);

        return medicineService.newMedicine(medicine);

    }

    @PutMapping
    public ResponseEntity<Object> updateMedicine(@RequestBody Medicine medicine){
        return medicineService.newMedicine(medicine);
    }

    @DeleteMapping("{medicineId}")
    public ResponseEntity<Object> deleteMedicine(@PathVariable("medicineId") Long medicineId){
        return this.medicineService.deleteMedicine(medicineId);
    }
}
