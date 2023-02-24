package com.mind.spring.controller.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.mind.spring.controller.LoginController;
import com.mind.spring.dto.LoginDTO;
import com.mind.spring.service.LoginService;

@Controller
public class LoginControllerImpl implements LoginController {

	@Autowired
	LoginService loginService;

	public ModelAndView showLogin(ModelAndView model) throws IOException {
		try {
			System.out.println("Login Controller Impl");
			model.setViewName("login");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in LoginControllerImpl- showLogin method: " + ex);
		}
		return model;
	}

	public ModelAndView loginProcess(LoginDTO loginDTO, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		try {
			LoginDTO lgDTO = new LoginDTO();
			lgDTO = loginService.validateUser(loginDTO);

			if (lgDTO != null && "success".equals(lgDTO.getStatus())) {
				request.getSession().setAttribute("loggedInUserRole", lgDTO.getUserRole());
				request.getSession().setAttribute("loggedInUserId", lgDTO.getUserId());
				model.setViewName("redirect:/showUser");
			} else if (lgDTO != null && "invalid".equals(lgDTO.getStatus())) {
				model.addObject("msg", "Invalid credentials");
				model.setViewName("login");
			} else {
				model.addObject("msg", "Error occured");
				model.setViewName("login");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in LoginControllerImpl-loginProcess method: " + ex);
		}
		return model;
	}
}
