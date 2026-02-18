package com.claim.hospital.service;

import com.claim.hospital.entity.Receipt;
import com.claim.hospital.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository repository;

    public ReceiptServiceImpl(ReceiptRepository repository) {
        this.repository = repository;
    }

    @Override
    public Receipt create(Receipt receipt) {
        receipt.setStatus("UNUSED");
        receipt.setCreatedAt(LocalDateTime.now());
        return repository.save(receipt);
    }

    @Override
    public List<Receipt> getUserReceipts(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public List<Receipt> getUnusedReceipts(String username) {
        return repository.findByUsernameAndStatus(username, "UNUSED");
    }
    @Override
    public void markClaimed(Long id) {
        Receipt receipt = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receipt not found"));

        receipt.setStatus("CLAIMED");
        repository.save(receipt);
    }

}
