package com.claim.insurance.controller;

import com.claim.insurance.entity.Policy;
import com.claim.insurance.service.PolicyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService service;

    public PolicyController(PolicyService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Policy create(@RequestBody Policy policy) {
        return service.create(policy);
    }

    @GetMapping("/all")
    public List<Policy> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Policy getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public Policy update(@PathVariable Long id,
                         @RequestBody Policy policy) {
        return service.update(id, policy);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
   

}
