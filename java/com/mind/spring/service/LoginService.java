package com.mind.spring.service;

import com.mind.spring.dto.LoginDTO;

public interface LoginService {

	public LoginDTO validateUser(LoginDTO loginDTO);
}
