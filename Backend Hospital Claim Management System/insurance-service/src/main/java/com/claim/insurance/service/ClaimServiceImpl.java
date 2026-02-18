package com.claim.insurance.service;

import com.claim.insurance.dto.AdminDashboardDTO;
import com.claim.insurance.dto.ReceiptDTO;
import com.claim.insurance.dto.UserDashboardDTO;
import com.claim.insurance.entity.Claim;
import com.claim.insurance.entity.Policy;
import com.claim.insurance.entity.UserPolicy;
import com.claim.insurance.exception.ResourceNotFoundException;
import com.claim.insurance.repository.ClaimRepository;
import com.claim.insurance.repository.PolicyRepository;
import com.claim.insurance.repository.UserPolicyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final PolicyRepository policyRepository;
    private final UserPolicyRepository userPolicyRepository;
    private final RestTemplate restTemplate;

    public ClaimServiceImpl(ClaimRepository claimRepository,
                            PolicyRepository policyRepository,
                            UserPolicyRepository userPolicyRepository,
                            RestTemplate restTemplate) {
        this.claimRepository = claimRepository;
        this.policyRepository = policyRepository;
        this.userPolicyRepository = userPolicyRepository;
        this.restTemplate = restTemplate;
    }

    // =====================================================
    // CREATE CLAIM
    // =====================================================
    @Override
    public Claim createClaim(String username, Long receiptId, Long policyId) {

        // 1️⃣ Validate receipt (UNUSED only)
        String receiptUrl = "http://localhost:9002/api/receipt/user/" + username + "/unused";
        ReceiptDTO[] receipts = restTemplate.getForObject(receiptUrl, ReceiptDTO[].class);

        ReceiptDTO validReceipt = null;

        if (receipts != null) {
            for (ReceiptDTO r : receipts) {
                if (r.getId().equals(receiptId)) {
                    validReceipt = r;
                    break;
                }
            }
        }

        if (validReceipt == null) {
            throw new RuntimeException("Receipt invalid or already claimed");
        }

        // 2️⃣ Get ACTIVE policy only
        UserPolicy activeUserPolicy = userPolicyRepository
                .findByUsernameAndActiveTrue(username)
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("No active policy attached"));

        // 3️⃣ Ensure selected policy is active one
        if (!activeUserPolicy.getPolicy().getId().equals(policyId)) {
            throw new RuntimeException("Selected policy is not active");
        }

        // 4️⃣ Prevent duplicate claim
        if (claimRepository.existsByReceiptId(receiptId)) {
            throw new RuntimeException("Receipt already claimed");
        }

        Policy policy = activeUserPolicy.getPolicy();

        // 5️⃣ Create claim
        Claim claim = new Claim();
        claim.setUsername(username);
        claim.setReceiptId(receiptId);
        claim.setPolicy(policy);
        claim.setAmount(validReceipt.getExpense());
        claim.setStatus("PENDING");
        claim.setCreatedAt(LocalDateTime.now());

        Claim savedClaim = claimRepository.save(claim);

        // 6️⃣ Mark receipt as CLAIMED in hospital-service
        restTemplate.put("http://localhost:9002/api/receipt/claim/" + receiptId, null);

        return savedClaim;
    }

    // =====================================================
    // USER CLAIMS
    // =====================================================
    @Override
    public List<Claim> getUserClaims(String username) {
        return claimRepository.findByUsername(username);
    }

    // =====================================================
    // ADMIN PENDING
    // =====================================================
    @Override
    public List<Claim> getAllPendingClaims() {
        return claimRepository.findByStatus("PENDING");
    }

    // =====================================================
    // APPROVE
    // =====================================================
    @Override
    public Claim approveClaim(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found"));

        claim.setStatus("APPROVED");
        return claimRepository.save(claim);
    }

    // =====================================================
    // REJECT
    // =====================================================
    @Override
    public Claim rejectClaim(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found"));

        claim.setStatus("REJECTED");
        return claimRepository.save(claim);
    }

    // =====================================================
    // ADMIN DASHBOARD
    // =====================================================
    @Override
    public AdminDashboardDTO getAdminDashboard() {

        long total = claimRepository.count();
        long pending = claimRepository.countByStatus("PENDING");
        long approved = claimRepository.countByStatus("APPROVED");
        long rejected = claimRepository.countByStatus("REJECTED");

        return new AdminDashboardDTO(total, pending, approved, rejected);
    }

    // =====================================================
    // USER DASHBOARD
    // =====================================================
    @Override
    public UserDashboardDTO getUserDashboard(String username) {

        long total = claimRepository.countByUsername(username);
        long approved = claimRepository.countByUsernameAndStatus(username, "APPROVED");
        long pending = claimRepository.countByUsernameAndStatus(username, "PENDING");
        long rejected = claimRepository.countByUsernameAndStatus(username, "REJECTED");

        Claim lastClaim = claimRepository.findTopByUsernameOrderByCreatedAtDesc(username);
        Double lastAmount = lastClaim != null ? lastClaim.getAmount() : null;

        // ✅ Correct way to fetch active policy
        UserPolicy activeUserPolicy =
                userPolicyRepository.findByUsernameAndActiveTrue(username)
                        .stream()
                        .findFirst()
                        .orElse(null);

        Policy activePolicy =
                activeUserPolicy != null ? activeUserPolicy.getPolicy() : null;

        return new UserDashboardDTO(
                activePolicy,
                total,
                approved,
                pending,
                rejected,
                lastAmount
        );
    }

}
