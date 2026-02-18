package com.claim.insurance.repository;

import com.claim.insurance.entity.UserPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPolicyRepository extends JpaRepository<UserPolicy, Long> {

    List<UserPolicy> findByUsername(String username);

    List<UserPolicy> findByUsernameAndActiveTrue(String username);
}
