package com.secure.Security1.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
	
	
	@GetMapping("/user")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String userAccess() {
		return ("<h1>user<h1>");
	}

	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String adminAccess() {
		return ("<h1>admin<h1>");
	}
}
