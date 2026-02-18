package com.claim.insurance.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    private LocalDateTime attachedDate;

    private Boolean active;

    public UserPolicy() {}

    public UserPolicy(Long id, String username, Policy policy,
                      LocalDateTime attachedDate, Boolean active) {
        this.id = id;
        this.username = username;
        this.policy = policy;
        this.attachedDate = attachedDate;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Policy getPolicy() { return policy; }
    public void setPolicy(Policy policy) { this.policy = policy; }

    public LocalDateTime getAttachedDate() { return attachedDate; }
    public void setAttachedDate(LocalDateTime attachedDate) { this.attachedDate = attachedDate; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
