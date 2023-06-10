package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MedicineType")
public class MedicineType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineTypeId;
    private String typeName;

    @OneToMany(mappedBy = "medicineType")
    @JsonIgnore
    private List<Medicine> medicines;

    public MedicineType() {
    }

    public MedicineType(Long medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }
    public MedicineType(Long medicineTypeId, String typeName) {
        this.medicineTypeId = medicineTypeId;
        this.typeName = typeName;
    }

    public MedicineType(String typeName, List<Medicine> medicines) {
        this.typeName = typeName;
        this.medicines = medicines;
    }

    public Long getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(Long medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}


