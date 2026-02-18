package com.claim.hospital.dto.claim;

import jakarta.validation.constraints.NotNull;
//import lombok.Data;

//@Data
public class ClaimRequestDTO {

    @NotNull(message = "Patient Id required")
    private Long patientId;

    @NotNull(message = "Claim amount required")
    private Double amount;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public ClaimRequestDTO(@NotNull(message = "Patient Id required") Long patientId,
			@NotNull(message = "Claim amount required") Double amount) {
		super();
		this.patientId = patientId;
		this.amount = amount;
	}
    
    public ClaimRequestDTO() {
    	
    }
}
