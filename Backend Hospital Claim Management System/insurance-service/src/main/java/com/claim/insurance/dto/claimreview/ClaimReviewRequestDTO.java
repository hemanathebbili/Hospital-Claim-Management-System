package com.claim.insurance.dto.claimreview;

//@Data
public class ClaimReviewRequestDTO {
    private Long claimId;
    private String status;
    private String remarks;
	public Long getClaimId() {
		return claimId;
	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public ClaimReviewRequestDTO(Long claimId, String status, String remarks) {
		super();
		this.claimId = claimId;
		this.status = status;
		this.remarks = remarks;
	}
    
    public ClaimReviewRequestDTO() {
    	
    }
}

