package com.claim.insurance.service;

import com.claim.insurance.entity.Receipt;
import java.util.List;

public interface ReceiptService {

    Receipt createReceipt(Receipt receipt);

    List<Receipt> getUserReceipts(String username);

    List<Receipt> getUnusedReceipts(String username);
}
