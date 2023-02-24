package com.mind.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mind.spring.dto.LoginDTO;

public interface LoginController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showLogin(ModelAndView model) throws IOException;

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(@ModelAttribute LoginDTO loginDTO, HttpServletRequest request);

}
