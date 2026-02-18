package com.claim.insurance.service;

import com.claim.insurance.entity.UserPolicy;
import java.util.List;

public interface UserPolicyService {

    UserPolicy attachPolicy(String username, Long policyId);

    List<UserPolicy> getUserPolicies(String username);
}
