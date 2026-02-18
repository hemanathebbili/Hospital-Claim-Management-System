package com.claim.hospital.service;

import com.claim.hospital.entity.Receipt;
import java.util.List;

public interface ReceiptService {

    Receipt create(Receipt receipt);

    List<Receipt> getUserReceipts(String username);

    List<Receipt> getUnusedReceipts(String username);

    void markClaimed(Long id);
}
