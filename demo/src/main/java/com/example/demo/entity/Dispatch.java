package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Dispatch")
public class Dispatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dispatchId;
    private int quantity;
    private String description;
    private LocalDate entryDate;
    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    public Dispatch() {

    }

    public Dispatch(Long dispatchId, int quantity, String description, LocalDate entryDate, LocalDate expiryDate, User user1, User user2, Medicine medicine) {
        this.dispatchId = dispatchId;
        this.quantity = quantity;
        this.description = description;
        this.entryDate = entryDate;
        this.expiryDate = expiryDate;
        this.user1 = user1;
        this.user2 = user2;
        this.medicine = medicine;
    }

    public Dispatch(int quantity, String description, LocalDate entryDate, LocalDate expiryDate, User user1, User user2, Medicine medicine) {
        this.quantity = quantity;
        this.description = description;
        this.entryDate = entryDate;
        this.expiryDate = expiryDate;
        this.user1 = user1;
        this.user2 = user2;
        this.medicine = medicine;
    }



    public Long getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Long dispatchId) {
        this.dispatchId = dispatchId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

}
