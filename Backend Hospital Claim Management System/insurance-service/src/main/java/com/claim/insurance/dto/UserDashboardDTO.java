package com.claim.insurance.dto;

import com.claim.insurance.entity.Policy;

public class UserDashboardDTO {

    private Policy activePolicy;
    private long totalClaims;
    private long approvedClaims;
    private long pendingClaims;
    private long rejectedClaims;
    private Double lastClaimAmount;

    public UserDashboardDTO(Policy activePolicy,
                            long totalClaims,
                            long approvedClaims,
                            long pendingClaims,
                            long rejectedClaims,
                            Double lastClaimAmount) {
        this.activePolicy = activePolicy;
        this.totalClaims = totalClaims;
        this.approvedClaims = approvedClaims;
        this.pendingClaims = pendingClaims;
        this.rejectedClaims = rejectedClaims;
        this.lastClaimAmount = lastClaimAmount;
    }

    public Policy getActivePolicy() { return activePolicy; }
    public long getTotalClaims() { return totalClaims; }
    public long getApprovedClaims() { return approvedClaims; }
    public long getPendingClaims() { return pendingClaims; }
    public long getRejectedClaims() { return rejectedClaims; }
    public Double getLastClaimAmount() { return lastClaimAmount; }
}
