package com.claim.insurance.service;

import java.util.List;
import com.claim.insurance.entity.Policy;

public interface PolicyService {

    Policy create(Policy policy);

    List<Policy> getAll();

    Policy getById(Long id);

    Policy update(Long id, Policy policy);

    void delete(Long id);
}
