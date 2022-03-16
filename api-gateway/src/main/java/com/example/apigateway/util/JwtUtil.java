package com.example.apigateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtil {

	private static final String SECRET_KEY = "hemsiemlak-patika-realy-secret-key-hemsiemlak-patika-realy-secret-key";

	private Key key;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	public Claims getAllClaimsFromToken(String token) {

		//// @formatter:off
		return Jwts
				.parserBuilder()
				.setSigningKey(key)			
				.build()
				.parseClaimsJws(token)
				.getBody();
			// @formatter:on

	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public String getUserName(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDate(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public boolean isTokenExpired(String token) {
		// try {
		return this.getExpirationDate(token).before(new Date());
//		} catch (Exception e) {
//			log.error("token is expired" + token);
//		}
//		return true;
	}

	public boolean isValidToken(String token) {
		// UserDetails userDetail =
		// customUserServiceDetail.loadUserByUsername(getUserName(token));
		try {
			return isTokenExpired(token);
		} catch (Exception e) {
			log.error("token is expired");
		}

		return true;
	}

}
