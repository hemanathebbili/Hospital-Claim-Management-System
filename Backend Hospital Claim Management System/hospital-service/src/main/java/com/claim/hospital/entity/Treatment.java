package com.claim.hospital.entity;

import jakarta.persistence.*;

@Entity
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double expense;

    public Treatment() {}

    public Treatment(Long id, String name, Double expense) {
        this.id = id;
        this.name = name;
        this.expense = expense;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getExpense() { return expense; }
    public void setExpense(Double expense) { this.expense = expense; }
}
