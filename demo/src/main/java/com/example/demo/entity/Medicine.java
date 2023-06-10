package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineId;
    private String commercialName;
    private String genericName;
    private BigDecimal costPrice;

    @OneToMany(mappedBy = "medicine")
    @JsonIgnore
    private List<Sale> sales;

    @OneToMany(mappedBy = "medicine")
    @JsonIgnore
    private List<Dispatch> dispatch;

    @OneToMany(mappedBy = "medicine")
    @JsonIgnore
    private List<Inventory> inventories;

    @ManyToOne
    @JoinColumn(name = "medicineType_id")
    private MedicineType medicineType;

    public Medicine() {
    }

    public Medicine(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Medicine(Long medicineId, String commercialName, String genericName, BigDecimal costPrice, MedicineType medicineType) {
        this.medicineId = medicineId;
        this.commercialName = commercialName;
        this.genericName = genericName;
        this.costPrice = costPrice;
        this.medicineType = medicineType;
    }

    public Medicine(String commercialName, String genericName, BigDecimal costPrice, MedicineType medicineType) {
        this.commercialName = commercialName;
        this.genericName = genericName;
        this.costPrice = costPrice;
        this.medicineType = medicineType;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public List<Dispatch> getDispatch() {
        return dispatch;
    }

    public void setDispatch(List<Dispatch> dispatch) {
        this.dispatch = dispatch;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    public MedicineType getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(MedicineType medicineType) {
        this.medicineType = medicineType;
    }

    public void setMedicine(Medicine medicine) {
    }
}
