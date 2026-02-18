package com.claim.insurance.service;

import com.claim.insurance.entity.Policy;
import com.claim.insurance.exception.ResourceNotFoundException;
import com.claim.insurance.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository repository;

    public PolicyServiceImpl(PolicyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Policy create(Policy policy) {
        return repository.save(policy);
    }

    @Override
    public List<Policy> getAll() {
        return repository.findAll();
    }

    @Override
    public Policy getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));
    }

    @Override
    public Policy update(Long id, Policy policy) {

        Policy existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));

        existing.setName(policy.getName());
        existing.setPremium(policy.getPremium());
        existing.setCoverage(policy.getCoverage());
        existing.setDescription(policy.getDescription());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {

        Policy existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));

        repository.delete(existing);
    }
}
