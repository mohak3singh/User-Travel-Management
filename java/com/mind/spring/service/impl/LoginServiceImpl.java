package com.mind.spring.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mind.spring.dao.LoginDAO;
import com.mind.spring.dto.LoginDTO;
import com.mind.spring.model.Login;
import com.mind.spring.service.LoginService;

@Component
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDAO loginDAO;

	@Override
	public LoginDTO validateUser(LoginDTO loginDTO) {
		try {
			Login login = new Login();
			BeanUtils.copyProperties(loginDTO, login);
			login = loginDAO.validateUser(login);
			loginDTO = new LoginDTO();
			if (login != null) {
				BeanUtils.copyProperties(login, loginDTO);
			} else {
				loginDTO = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in LoginServiceImpl-validateUser method: " + ex);
		}
		return loginDTO;
	}
}
