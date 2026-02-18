package com.claim.insurance.controller;

import com.claim.insurance.entity.UserPolicy;
import com.claim.insurance.service.UserPolicyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-policies")
public class UserPolicyController {

    private final UserPolicyService service;

    public UserPolicyController(UserPolicyService service) {
        this.service = service;
    }

    @PostMapping("/attach/{username}/{policyId}")
    public UserPolicy attachPolicy(@PathVariable String username,
                                   @PathVariable Long policyId) {
        return service.attachPolicy(username, policyId);
    }

    @GetMapping("/{username}")
    public List<UserPolicy> getUserPolicies(@PathVariable String username) {
        return service.getUserPolicies(username);
    }
}
