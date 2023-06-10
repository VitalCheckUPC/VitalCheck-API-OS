package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;
    private int quantity;
    private BigDecimal salePrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    public Inventory() {

    }


    public Inventory(Long inventoryId, int quantity, BigDecimal salePrice, User user, Medicine medicine) {
        this.inventoryId = inventoryId;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.user = user;
        this.medicine = medicine;
    }

    public Inventory(int quantity, BigDecimal salePrice, User user, Medicine medicine) {
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.user = user;
        this.medicine = medicine;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}
