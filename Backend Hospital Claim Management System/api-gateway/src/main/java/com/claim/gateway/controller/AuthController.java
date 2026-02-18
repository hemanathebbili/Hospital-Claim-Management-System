package com.claim.gateway.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional ;              


import com.claim.gateway.dto.AuthResponse;
import com.claim.gateway.dto.LoginRequest;
import com.claim.gateway.dto.SignupRequest;
import com.claim.gateway.entity.User;
import com.claim.gateway.repository.UserRepository;
import com.claim.gateway.security.JwtUtil;


@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    
    

    public AuthController(UserRepository repo, PasswordEncoder encoder, JwtUtil jwtUtil) {
		super();
		this.repo = repo;
		this.encoder = encoder;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/signup")
    public String signup(@RequestBody SignupRequest req) {

        if(repo.findByUsername(req.getUsername()).isPresent())
            return "User already exists";

        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setRole(req.getRole());

        repo.save(u);

        return "User created";
    }
	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest req) {

	    Optional<User> optional = repo.findByUsername(req.getUsername()); // ⭐ FIXED

	    if(optional.isEmpty()) {
	        throw new RuntimeException("User not found");
	    }

	    User user = optional.get();

	    if(!encoder.matches(req.getPassword(), user.getPassword()))
	        throw new RuntimeException("Invalid password");

	    String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

	    return new AuthResponse(token);
	}



}
