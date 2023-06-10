package com.example.demo.service;

import com.example.demo.entity.Medicine;
import com.example.demo.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {
    private HashMap<String, Object> medicineData;
    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineService(MedicineRepository medicineRepository){
        this.medicineRepository = medicineRepository;
        this.medicineData = new HashMap<>();
    }

    public List<Medicine> getMedicine(){
        return this.medicineRepository.findAll();
    }

    public Optional<Medicine> getMedicine(Long medicineId){
        return this.medicineRepository.findById(medicineId);
    }

    public ResponseEntity<Object> newMedicine(Medicine medicine){
        Optional<Medicine> mcn = medicineRepository.findByCommercialName(medicine.getCommercialName());
        Optional<Medicine> mgn = medicineRepository.findByGenericName(medicine.getGenericName());

        if (mcn.isPresent() && medicine.getMedicineId() == null || mgn.isPresent() && medicine.getMedicineId() == null){
            medicineData.put("Error", true);
            medicineData.put("message","One medicine already exist");
            return new ResponseEntity<>(
                    medicineData,
                    HttpStatus.CONFLICT
            );
        }
        medicineData.put("message", "Save");
        if (medicine.getMedicineId() != null ){
            medicineData.put("message", "Update");
        }
        medicineRepository.save(medicine);
        medicineData.put("medicineData", medicine);
        medicineData.put("message", "Save");
        return new ResponseEntity<>(
                medicineData,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteMedicine(Long medicineId){
        medicineData = new HashMap<>();
        boolean exits = this.medicineRepository.existsById(medicineId);
        if (!exits){
            medicineData.put("Error", true);
            medicineData.put("message", "Medicine with specified ID does not exist");
            return new ResponseEntity<>(
                    medicineData,
                    HttpStatus.CONFLICT
            );
        }
        medicineRepository.deleteById(medicineId);
        medicineData.put("message", "Medicine Eliminated");
        return new ResponseEntity<>(
                medicineData,
                HttpStatus.ACCEPTED
        );
    }
}
