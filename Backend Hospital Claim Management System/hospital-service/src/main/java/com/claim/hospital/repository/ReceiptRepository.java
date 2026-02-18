package com.claim.hospital.repository;

import com.claim.hospital.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    List<Receipt> findByUsername(String username);

    List<Receipt> findByUsernameAndStatus(String username, String status);
}
