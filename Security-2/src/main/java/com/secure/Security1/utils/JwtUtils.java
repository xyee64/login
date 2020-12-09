package com.secure.Security1.utils;

import java.util.*;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;

@Service
public class JwtUtils {

	@Value("${security.app.jwtSecret}")
	private String jwtSecret;
	@Value("${security.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	
		public String extractUsername(String token) {
			return extractClaim(token, Claims::getSubject);
		}
	
		public Date extractExpiration(String token) {
			return extractClaim(token, Claims::getExpiration);
		}
		public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
			final Claims claims = extractAllClaims(token);
			return claimsResolver.apply(claims);
		}
	   
		private Claims extractAllClaims(String token) {
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		}
	
		private Boolean isTokenExpired(String token) {
			final Date expiration = extractExpiration(token);
			return expiration.before(new Date());
		}

		public String generateToken(UserDetails userDetails) {
			Map<String, Object> claims = new HashMap<>();
			return createToken(claims, userDetails.getUsername());
		}

		private String createToken(Map<String, Object> claims, String subject) {
			return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
					.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		}

		public Boolean validateToken(String token, UserDetails userDetails) {
			final String username = extractUsername(token);
			return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		}	
}
