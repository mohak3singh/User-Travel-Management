package com.mind.spring.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mind.spring.controller.RequestController;
import com.mind.spring.dto.TravelRequestDTO;
import com.mind.spring.service.RequestService;

@Controller
public class RequestControllerImpl implements RequestController {

	@Autowired
	RequestService requestService;

	public ModelAndView createRequest(ModelAndView model, HttpServletRequest request) throws IOException {
		try {
			model.setViewName("travelForm");
			model.addObject("action", "create");
			model.addObject("loggedInUserId", request.getSession().getAttribute("loggedInUserId"));
			model.addObject("loggedInTravellerId", request.getSession().getAttribute("loggedInTravellerId"));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestControllerImpl-createRequest method: " + ex);
		}
		return model;
	}

	public @ResponseBody TravelRequestDTO getTravellerInfoById(@PathVariable int userId) {
		TravelRequestDTO travelRequestDTO = null;
		try {
			travelRequestDTO = requestService.getTravellerInfoById(userId);
			ModelAndView model = new ModelAndView("travelForm");
			model.addObject("travelRequestDTO", travelRequestDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestControllerImpl-getTravellerInfoById method: " + ex);
		}
		return travelRequestDTO;
	}

	public @ResponseBody TravelRequestDTO saveTravelRequest(@RequestBody TravelRequestDTO travelRequestDTO) {
		String status = null;
		try {
			status = requestService.saveOrUpdateTravelRequest(travelRequestDTO);
			if (status != null && "0".equals(status)) {
				travelRequestDTO.setStatus("0");
			} else if (status != null && "1".equals(status)) {
				travelRequestDTO.setStatus("1");
			} else if (status != null && "2".equals(status)) {
				travelRequestDTO.setStatus("2");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestControllerImpl-saveTravelRequest method: " + ex);
		}
		return travelRequestDTO;
	}

	public ModelAndView travelRequestList(ModelAndView model, HttpServletRequest request) throws IOException {
		try {
			model.addObject("loggedInUserRole", request.getSession().getAttribute("loggedInUserRole"));
			model.addObject("loggedInUserId", request.getSession().getAttribute("loggedInUserId"));
			model.addObject("action", "list");
			model.setViewName("travelRequestList");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestControllerImpl-travelRequestList method: " + ex);
		}
		return model;
	}

	public @ResponseBody List<TravelRequestDTO> getTravelRequestList(HttpServletRequest request) throws IOException {
		List<TravelRequestDTO> requestDTOList = null;
		try {
			String userRole = request.getParameter("loggedInUserRole");
			String travellerId = request.getParameter("loggedInUserId");
			requestDTOList = requestService.getTravelRequestList(userRole, travellerId);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestControllerImpl-getTravelRequestList method: " + ex);
		}
		return requestDTOList;
	}

	public ModelAndView editRequestList(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
			String travellerId = request.getParameter("travellerId");
			String travelId = request.getParameter("travelId");
			String unitId = request.getParameter("unitId");
			model = new ModelAndView("travelForm");
			model.addObject("travellerId", travellerId);
			model.addObject("travelId", travelId);
			model.addObject("unitId", unitId);
			model.addObject("action", "edit");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestControllerImpl-editRequestList method: " + ex);
		}
		return model;
	}

	public @ResponseBody TravelRequestDTO getRequestDetailForEdit(@PathVariable int travellerId,
			@PathVariable int travelId, @PathVariable int unitId) {
		TravelRequestDTO travelRequestDTO = null;
		try {
			travelRequestDTO = requestService.getRequestDetailForEdit(travellerId, travelId, unitId);
			ModelAndView model = new ModelAndView("travelForm");
			model.addObject("travelRequestDTO", travelRequestDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestControllerImpl-getRequestDetailForEdit method: " + ex);
		}
		return travelRequestDTO;
	}

	public @ResponseBody String deleteRequestListUser(HttpServletRequest request) {
		String status = null;
		try {
			String travellerId = request.getParameter("travellerId");
			String travelId = request.getParameter("travelId");
			String unitId = request.getParameter("unitId");
			status = requestService.deleteRequestListUser(travellerId, travelId, unitId);
			if (status != null && "0".equals(status)) {
				/*status = "List deleted successfully";*/
			} else if (status != null && "1".equals(status)) {
				/*status = "List not deleted - Error occured";*/
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestControllerImpl-deleteRequestListUser method: " + ex);
		}
		return status;
	}

	public ModelAndView getRequestDetail(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
			String travellerId = request.getParameter("travellerId");
			String travelId = request.getParameter("travelId");
			String unitId = request.getParameter("unitId");
			model = new ModelAndView("requestDetailTable");
			model.addObject("travellerId", travellerId);
			model.addObject("travelId", travelId);
			model.addObject("unitId", unitId);
			model.addObject("action", "detail");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestControllerImpl-getRequestTableInfo method: " + ex);
		}
		return model;
	}
}
