package com.claim.insurance.controller;

import com.claim.insurance.dto.AdminDashboardDTO;
import com.claim.insurance.dto.UserDashboardDTO;
import com.claim.insurance.entity.Claim;
import com.claim.insurance.service.ClaimService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService service;

    public ClaimController(ClaimService service) {
        this.service = service;
    }

    @PostMapping("/create/{username}/{receiptId}/{policyId}")
    public Claim createClaim(@PathVariable String username,
                             @PathVariable Long receiptId,
                             @PathVariable Long policyId) {
        return service.createClaim(username, receiptId, policyId);
    }

    @GetMapping("/user/{username}")
    public List<Claim> getUserClaims(@PathVariable String username) {
        return service.getUserClaims(username);
    }

    @GetMapping("/pending")
    public List<Claim> getPendingClaims() {
        return service.getAllPendingClaims();
    }

    @PutMapping("/approve/{claimId}")
    public Claim approveClaim(@PathVariable Long claimId) {
        return service.approveClaim(claimId);
    }

    @PutMapping("/reject/{claimId}")
    public Claim rejectClaim(@PathVariable Long claimId) {
        return service.rejectClaim(claimId);
    }

    @GetMapping("/admin/dashboard")
    public AdminDashboardDTO getDashboard() {
        return service.getAdminDashboard();
    }

    @GetMapping("/user/dashboard/{username}")
    public UserDashboardDTO getUserDashboard(@PathVariable String username) {
        return service.getUserDashboard(username);
    }
}
