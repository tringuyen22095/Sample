package com.personal.ctrl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.config.JwtManager;
import com.personal.entity.UserAccountEntity;
import com.personal.model.res.AuthRes;

@RestController
@RequestMapping("auth")
public class AuthController {
	private static final Logger _log = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private JwtManager jwtManager;

	@PostMapping(value = "sign-in")
	public ResponseEntity<AuthRes> signIn(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		_log.info("Request login as {}", username);

		UserAccountEntity user = new UserAccountEntity();
		user.setUsername(username);
		user.setRoles(Arrays.asList(password));

		return ResponseEntity.ok(this.jwtManager.generateToken(user));
	}

}
