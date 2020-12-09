package com.secure.Security1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.secure.Security1.request.LoginRequest;
import com.secure.Security1.response.jwtResponse;
import com.secure.Security1.service.MyUserDetails;
import com.secure.Security1.service.UserDetailsServiceImpl;
import com.secure.Security1.utils.JwtUtils;



@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@GetMapping("/")
	public String welcome() {
		return ("<h1>welcome<h1>");
	}
	
	@RequestMapping(value = "/auth", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken (@RequestBody LoginRequest loginRequest) throws Exception{
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
			}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(loginRequest.getUsername());
		final String jwt = jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new jwtResponse(jwt));
	}
}
