package com.secure.Security1.response;

public class jwtResponse {

	private String jwt;

	public jwtResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}	
}
