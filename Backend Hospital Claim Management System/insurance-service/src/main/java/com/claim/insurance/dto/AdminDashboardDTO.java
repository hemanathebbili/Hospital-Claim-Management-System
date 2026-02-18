package com.claim.insurance.dto;

public class AdminDashboardDTO {

    private long totalClaims;
    private long pendingClaims;
    private long approvedClaims;
    private long rejectedClaims;

    public AdminDashboardDTO(long totalClaims,
                             long pendingClaims,
                             long approvedClaims,
                             long rejectedClaims) {
        this.totalClaims = totalClaims;
        this.pendingClaims = pendingClaims;
        this.approvedClaims = approvedClaims;
        this.rejectedClaims = rejectedClaims;
    }

    public long getTotalClaims() { return totalClaims; }
    public long getPendingClaims() { return pendingClaims; }
    public long getApprovedClaims() { return approvedClaims; }
    public long getRejectedClaims() { return rejectedClaims; }
}
