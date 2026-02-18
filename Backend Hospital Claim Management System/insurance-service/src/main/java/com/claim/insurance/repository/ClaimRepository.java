package com.claim.insurance.repository;

import com.claim.insurance.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    List<Claim> findByUsername(String username);

    List<Claim> findByStatus(String status);

    long count();

    long countByStatus(String status);
    
    long countByUsername(String username);

    long countByUsernameAndStatus(String username, String status);

    Claim findTopByUsernameOrderByCreatedAtDesc(String username);
    
    boolean existsByReceiptId(Long receiptId);


}
