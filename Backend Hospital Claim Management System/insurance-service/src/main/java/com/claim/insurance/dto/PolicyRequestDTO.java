package com.claim.insurance.dto;

//@Data
public class PolicyRequestDTO {
    private Long patientId;
    private String policyNumber;
    private Double coverageAmount;
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
	public PolicyRequestDTO(Long patientId, String policyNumber, Double coverageAmount) {
		super();
		this.patientId = patientId;
		this.policyNumber = policyNumber;
		this.coverageAmount = coverageAmount;
	}
    
    public PolicyRequestDTO() {
    	
    }
}

