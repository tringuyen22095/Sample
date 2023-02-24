package com.personal.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.personal.Constants;
import com.personal.entity.UserAccountEntity;
import com.personal.model.res.AuthRes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtManager {
	
	@Value("${jwt.token.expiry.minutes:30}")
	private Long expireMinutes;

	@Value("${jwt.internal.secret:e@k&j#RCXbtykDh$t80d@Y5T4}")
	private String authSecret;
	
	private String preAuthKeyBase64;

	@PostConstruct
	public void init() {
		this.preAuthKeyBase64 = Base64.getEncoder().encodeToString(this.authSecret.getBytes(StandardCharsets.UTF_8));
	}
	
	public Claims parseToken(String token) {
		if (StringUtils.isBlank(token)) {
			return null;
		}
		return Jwts.parser()
				.setSigningKey(this.preAuthKeyBase64)
				.parseClaimsJws(StringUtils.removeStart(token, Constants.TOKEN_PREFIX))
				.getBody();
	}
	
	public AuthRes generateToken(UserAccountEntity user) {
		
		Claims claims = Jwts.claims()
				.setSubject(user.getUsername());
		claims.put("authorities", user.getRoles()
										.stream()
										.map(i -> String.format("ROLE_%s", i.toUpperCase()))
										.collect(Collectors.toSet()));

		DateTime current = DateTime.now();
		DateTime expiredAt = current.plusMinutes(this.expireMinutes.intValue());
		
		String token = Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(current.toDate())
				.setExpiration(expiredAt.toDate())
				.signWith(SignatureAlgorithm.HS512, this.preAuthKeyBase64).compact();

		return AuthRes.builder()
				.withToken(token)
				.build();
	}
	
	public boolean isTokenExpired(String token) {
		DateTime expireTime = new DateTime(this.parseToken(token).getExpiration());
		return expireTime.isBeforeNow();
	}
	
	public String[] getAuthorities(String token) {
		List<?> roles = this.parseToken(token).get("authorities", List.class);
		String[] arr = new String[roles.size()];
		roles.stream()
				.map(Object::toString)
				.collect(Collectors.toList())
				.toArray(arr);
		return arr;
	}

}
