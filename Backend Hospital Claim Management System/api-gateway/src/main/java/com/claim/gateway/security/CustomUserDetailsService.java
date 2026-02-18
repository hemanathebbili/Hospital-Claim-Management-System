package com.claim.gateway.security;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.claim.gateway.entity.User;
import com.claim.gateway.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;
    

	public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

	@Override
	public UserDetails loadUserByUsername(String username) {

	    Optional<User> optional = repo.findByUsername(username);

	    if(optional.isEmpty()) {
	        throw new RuntimeException("User not found");
	    }

	    User user = optional.get();

	    return new org.springframework.security.core.userdetails.User(
	            user.getUsername(),
	            user.getPassword(),
	            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
	    );
	}

}



