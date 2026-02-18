package com.claim.insurance.controller;

import com.claim.insurance.entity.Receipt;
import com.claim.insurance.service.ReceiptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {

    private final ReceiptService service;

    public ReceiptController(ReceiptService service) {
        this.service = service;
    }

    // Create Receipt
    @PostMapping("/create")
    public Receipt create(@RequestBody Receipt receipt) {
        return service.createReceipt(receipt);
    }

    // Get All Receipts for User
    @GetMapping("/user/{username}")
    public List<Receipt> getUserReceipts(@PathVariable String username) {
        return service.getUserReceipts(username);
    }

    // Get UNUSED Receipts
    @GetMapping("/user/{username}/unused")
    public List<Receipt> getUnused(@PathVariable String username) {
        return service.getUnusedReceipts(username);
    }
}
