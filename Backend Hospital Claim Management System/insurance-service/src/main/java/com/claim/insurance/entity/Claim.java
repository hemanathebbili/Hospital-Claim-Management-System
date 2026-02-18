package com.claim.insurance.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private Long receiptId;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    private Double amount;

    private String status; // PENDING / APPROVED / REJECTED

    private LocalDateTime createdAt;

    public Claim() {}

    public Claim(Long id, String username, Long receiptId,
                 Policy policy, Double amount,
                 String status, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.receiptId = receiptId;
        this.policy = policy;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Long getReceiptId() { return receiptId; }
    public void setReceiptId(Long receiptId) { this.receiptId = receiptId; }

    public Policy getPolicy() { return policy; }
    public void setPolicy(Policy policy) { this.policy = policy; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
