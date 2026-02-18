package com.claim.hospital.controller;

import com.claim.hospital.entity.Receipt;
import com.claim.hospital.service.ReceiptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {

    private final ReceiptService service;

    public ReceiptController(ReceiptService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Receipt create(@RequestBody Receipt receipt) {
        return service.create(receipt);
    }

    @GetMapping("/user/{username}")
    public List<Receipt> getUserReceipts(@PathVariable String username) {
        return service.getUserReceipts(username);
    }

    @GetMapping("/user/{username}/unused")
    public List<Receipt> getUnusedReceipts(@PathVariable String username) {
        return service.getUnusedReceipts(username);
    }

    @PutMapping("/claim/{id}")
    public void markClaimed(@PathVariable Long id) {
        service.markClaimed(id);
    }
}
