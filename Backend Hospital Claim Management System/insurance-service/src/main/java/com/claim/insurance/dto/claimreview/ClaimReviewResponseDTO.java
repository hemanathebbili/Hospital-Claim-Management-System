package com.claim.insurance.dto.claimreview;

//@Data
public class ClaimReviewResponseDTO {
    private Long id;
    private Long claimId;
    private String status;
    private String remarks;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public ClaimReviewResponseDTO(Long id, Long claimId, String status, String remarks) {
		super();
		this.id = id;
		this.claimId = claimId;
		this.status = status;
		this.remarks = remarks;
	}
    
    
	public ClaimReviewResponseDTO() {
		
	}
}
