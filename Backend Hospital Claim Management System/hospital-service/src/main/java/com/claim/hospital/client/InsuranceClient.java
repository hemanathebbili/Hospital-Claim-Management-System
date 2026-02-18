package com.claim.hospital.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "insurance-service")
public interface InsuranceClient {

    @PostMapping("/insurance/validate/{claimId}")
    String validateClaim(@PathVariable Long claimId);
}
