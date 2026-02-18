package com.claim.insurance.service;

import com.claim.insurance.dto.AdminDashboardDTO;
import com.claim.insurance.dto.UserDashboardDTO;
import com.claim.insurance.entity.Claim;

import java.util.List;

public interface ClaimService {

    Claim createClaim(String username, Long receiptId, Long policyId);

    List<Claim> getUserClaims(String username);

    List<Claim> getAllPendingClaims();

    Claim approveClaim(Long claimId);

    Claim rejectClaim(Long claimId);

    AdminDashboardDTO getAdminDashboard();

    UserDashboardDTO getUserDashboard(String username);
}
