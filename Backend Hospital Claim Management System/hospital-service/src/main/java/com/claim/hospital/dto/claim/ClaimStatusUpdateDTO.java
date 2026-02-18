package com.claim.hospital.dto.claim;

//import lombok.Data;

//@Data
public class ClaimStatusUpdateDTO {

    private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ClaimStatusUpdateDTO(String status) {
		super();
		this.status = status;
	}
    
    public ClaimStatusUpdateDTO() {
    	
    }
}
