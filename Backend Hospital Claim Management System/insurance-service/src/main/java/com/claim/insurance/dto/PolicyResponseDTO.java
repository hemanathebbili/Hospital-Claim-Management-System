package com.claim.insurance.dto;

//@Data
public class PolicyResponseDTO {
    private Long id;
    private Long patientId;
    private String policyNumber;
    private Double coverageAmount;
    private Boolean active;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public Double getCoverageAmount() {
		return coverageAmount;
	}
	public void setCoverageAmount(Double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public PolicyResponseDTO(Long id, Long patientId, String policyNumber, Double coverageAmount, Boolean active) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.policyNumber = policyNumber;
		this.coverageAmount = coverageAmount;
		this.active = active;
	}
    
    public PolicyResponseDTO() {
    	
    }
}

