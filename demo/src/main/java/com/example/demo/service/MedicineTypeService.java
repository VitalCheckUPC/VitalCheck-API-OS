package com.example.demo.service;

import com.example.demo.entity.MedicineType;
import com.example.demo.repository.MedicineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineTypeService {
    HashMap<String, Object> medicineTypeData;
    private final MedicineTypeRepository medicineTypeRepository;

    @Autowired
    public  MedicineTypeService(MedicineTypeRepository medicineTypeRepository){
        this.medicineTypeRepository = medicineTypeRepository;
    }

    public List<MedicineType> getMedicineType(){
        return this.medicineTypeRepository.findAll();
    }

    public Optional<MedicineType> getMedicineType(Long medicineTypeId){
        return this.medicineTypeRepository.findById(medicineTypeId);
    }

    public ResponseEntity<Object> newMedicineType(MedicineType medicineType){
        Optional<MedicineType> mt = medicineTypeRepository.findByTypeName(medicineType.getTypeName());
        medicineTypeData = new HashMap<>();

        if(mt.isPresent() && medicineType.getMedicineTypeId() == null){
            medicineTypeData.put("Error", true);
            medicineTypeData.put("message", "One medicine type already exist");
            return new ResponseEntity<>(
                    medicineTypeData,
                    HttpStatus.CONFLICT
            );
        }
        medicineTypeData.put("message", "Save");
        if (medicineType.getMedicineTypeId() != null){
            medicineTypeData.put("message", "Update");
        }
        medicineTypeRepository.save(medicineType);
        medicineTypeData.put("medicineTypeData", medicineType);
        medicineTypeData.put("message", "Save");
        return new ResponseEntity<>(
                medicineTypeData,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteMedicineType(Long medicineTypeId){
        medicineTypeData = new HashMap<>();
        boolean exists = this.medicineTypeRepository.existsById(medicineTypeId);
        if (!exists){
            medicineTypeData.put("Error", true);
            medicineTypeData.put("message", "Medicine Type with specified ID does not exist");
            return new ResponseEntity<>(
                    medicineTypeData,
                    HttpStatus.CONFLICT
            );
        }
        medicineTypeRepository.deleteById(medicineTypeId);
        medicineTypeData.put("message", "Medicine Type Eliminated");
        return new ResponseEntity<>(
                medicineTypeData,
                HttpStatus.ACCEPTED
        );
    }
}
