package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;
    private int quantity;
    private BigDecimal totalPrice;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    public Sale() {
    }


    public Sale(Long saleId, int quantity, BigDecimal totalPrice, LocalDate date, User user, Client client, Medicine medicine) {
        this.saleId = saleId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
        this.user = user;
        this.client = client;
        this.medicine = medicine;
    }

    public Sale(int quantity, BigDecimal totalPrice, LocalDate date, User user, Client client, Medicine medicine) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
        this.user = user;
        this.client = client;
        this.medicine = medicine;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}
