package com.personal.config;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.removeStart;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.personal.Constants;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
	private final Logger _log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

	@Autowired
	private JwtManager jwtManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String token = ofNullable(req.getHeader(Constants.AUTHORIZATION))
				.map(value -> removeStart(value, Constants.TOKEN_PREFIX))
				.map(String::trim)
				.orElse(StringUtils.EMPTY);

		if (isNotBlank(token)) {
			try {
				if (!this.jwtManager.isTokenExpired(token)) {
					Authentication auth = new UsernamePasswordAuthenticationToken(token, null, AuthorityUtils.createAuthorityList(this.jwtManager.getAuthorities(token)));
					SecurityContextHolder.getContext().setAuthentication(auth);
				} else
					throw new Exception();
			} catch (Exception ex) {
				_log.error("Json web token has expired or invalid.");
				SecurityContextHolder.clearContext();
			}
		} else {
			_log.error("Missing authorization token.");
			SecurityContextHolder.clearContext();
		}

		chain.doFilter(req, res);
	}
}
