package com.mind.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mind.spring.dto.TravelRequestDTO;
import com.mind.spring.dto.UserDTO;

public interface RequestController {

	@RequestMapping(value = "/createRequest", method = RequestMethod.GET)
	public ModelAndView createRequest(ModelAndView model, HttpServletRequest request) throws IOException;
	
	@RequestMapping(value = "/getTravellerInfoById/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody TravelRequestDTO getTravellerInfoById(@PathVariable int userId);
	
	@RequestMapping(value = "/saveTravelRequest", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody TravelRequestDTO saveTravelRequest(@RequestBody TravelRequestDTO travelRequestDTO);
	
	@RequestMapping(value = "/travelRequestList", method = RequestMethod.GET)
	public ModelAndView travelRequestList(ModelAndView model, HttpServletRequest request) throws IOException;
	
	@RequestMapping(value = "/getTravelRequestList", method = RequestMethod.GET)
	public @ResponseBody List<TravelRequestDTO> getTravelRequestList(HttpServletRequest request) throws IOException;
	
	@RequestMapping(value = "/editRequestList", method = RequestMethod.GET)
	public ModelAndView editRequestList(HttpServletRequest request);
	
	@RequestMapping(value = "/getRequestDetailForEdit/{travellerId}/{travelId}/{unitId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody TravelRequestDTO getRequestDetailForEdit(@PathVariable int travellerId,@PathVariable int travelId,@PathVariable int unitId);
	
	@RequestMapping(value = "/deleteRequestListUser", method = RequestMethod.POST)
	public @ResponseBody String deleteRequestListUser(HttpServletRequest request);
	
	@RequestMapping(value = "/getRequestDetail", method = RequestMethod.GET)
	public ModelAndView getRequestDetail(HttpServletRequest request);
}
