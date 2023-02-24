package com.personal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.personal.service.AuthService;
import com.personal.util.ModelMapper;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	@Qualifier(value = "mapper")
	private ModelMapper mapper;

	@Override
	public void signIn(String username, String password) {

	}

}
