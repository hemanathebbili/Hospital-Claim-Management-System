package com.claim.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.claim.insurance.entity.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    List<Receipt> findByUsername(String username);

    List<Receipt> findByUsernameAndStatus(String username, String status);
}
