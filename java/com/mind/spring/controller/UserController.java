package com.mind.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mind.spring.dto.UnitDTO;
import com.mind.spring.dto.UserDTO;

public interface UserController {

	@RequestMapping(value = "/showUser", method = RequestMethod.GET)
	public ModelAndView showUser(ModelAndView model, HttpServletRequest request) throws IOException;

	@RequestMapping(value = "/viewList", method = RequestMethod.GET)
	public ModelAndView viewList(ModelAndView model, HttpServletRequest request) throws IOException;

	@RequestMapping(value = "/getList", method = RequestMethod.GET, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserDTO> getUserList(HttpServletRequest request) throws IOException;

	@RequestMapping(value = "/getViewList", method = RequestMethod.GET)
	public @ResponseBody List<UserDTO> getViewList(HttpServletRequest request) throws IOException;

	@RequestMapping(value = "/getUnitList", method = RequestMethod.GET)
	public @ResponseBody List<UnitDTO> getUnitList() throws IOException;

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public @ResponseBody ModelAndView addUser(ModelAndView model, HttpServletRequest request);

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody UserDTO saveUser(@RequestBody UserDTO userDTO);

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request);

	@RequestMapping(value = "/getUserById/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody UserDTO getUserById(@PathVariable int userId);

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public @ResponseBody String deleteUser(HttpServletRequest request);

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody ModelAndView logoutUser(ModelAndView model, HttpServletRequest request);

}
