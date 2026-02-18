package com.claim.insurance.service;

import com.claim.insurance.entity.Policy;
import com.claim.insurance.entity.UserPolicy;
import com.claim.insurance.repository.PolicyRepository;
import com.claim.insurance.repository.UserPolicyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserPolicyServiceImpl implements UserPolicyService {

    private final UserPolicyRepository userPolicyRepository;
    private final PolicyRepository policyRepository;

    public UserPolicyServiceImpl(UserPolicyRepository userPolicyRepository,
                                 PolicyRepository policyRepository) {
        this.userPolicyRepository = userPolicyRepository;
        this.policyRepository = policyRepository;
    }

    @Override
    public UserPolicy attachPolicy(String username, Long policyId) {

        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        // 🔥 Deactivate all existing active policies for this user
        List<UserPolicy> existingPolicies = userPolicyRepository.findByUsername(username);

        for (UserPolicy up : existingPolicies) {
            if (Boolean.TRUE.equals(up.getActive())) {
                up.setActive(false);
                userPolicyRepository.save(up);
            }
        }

        // ✅ Attach new policy as active
        UserPolicy userPolicy = new UserPolicy();
        userPolicy.setUsername(username);
        userPolicy.setPolicy(policy);
        userPolicy.setAttachedDate(LocalDateTime.now());
        userPolicy.setActive(true);

        return userPolicyRepository.save(userPolicy);
    }

    @Override
    public List<UserPolicy> getUserPolicies(String username) {
        return userPolicyRepository.findByUsername(username);
    }
}
