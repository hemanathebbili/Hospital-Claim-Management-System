package com.claim.insurance.service;

import com.claim.insurance.entity.Receipt;
import com.claim.insurance.repository.ReceiptRepository;
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
    public Receipt createReceipt(Receipt receipt) {
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
}
