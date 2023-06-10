package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    @Column(unique = true, nullable = false)
    @NotNull
    private String dni;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "client")
    @JsonBackReference
    @JsonIgnore
    private List<Sale> sales;
    public Client() {

    }

    public Client(Long clientId) {
        this.clientId = clientId;
    }

    public Client(Long clientId, String  dni, String firstName, String lastName) {
        this.clientId = clientId;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client(String dni, String firstName, String lastName) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setClient(Client client){
    }
}
