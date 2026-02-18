package com.claim.insurance.dto;

public class ReceiptDTO {

    private Long id;
    private String username;
    private Long hospitalId;
    private String hospitalName;
    private String treatmentName;
    private Double expense;
    private String status;

    public ReceiptDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Long getHospitalId() { return hospitalId; }
    public void setHospitalId(Long hospitalId) { this.hospitalId = hospitalId; }

    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }

    public String getTreatmentName() { return treatmentName; }
    public void setTreatmentName(String treatmentName) { this.treatmentName = treatmentName; }

    public Double getExpense() { return expense; }
    public void setExpense(Double expense) { this.expense = expense; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
