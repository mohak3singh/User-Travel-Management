package com.mind.spring.controller.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mind.spring.controller.UserController;
import com.mind.spring.dto.UnitDTO;
import com.mind.spring.dto.UserDTO;
import com.mind.spring.service.UserService;

@Controller
public class UserControllerImpl implements UserController {

	@Autowired
	UserService userService;

	public ModelAndView showUser(ModelAndView model, HttpServletRequest request) throws IOException {
		try {
			String status = request.getParameter("status");
			if (status != null && "1".equals(status)) {
				status = "user added successfully";
			} else if (status != null && "2".equals(status)) {
				status = "user updated successfully";
			}
			model.addObject("msg", status);
			model.setViewName("home");
			model.addObject("loggedInUserRole", request.getSession().getAttribute("loggedInUserRole"));
			model.addObject("loggedInUserId", request.getSession().getAttribute("loggedInUserId"));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserControllerImpl-showUser method: " + ex);
		}
		return model;
	}

	public @ResponseBody List<UserDTO> getUserList(HttpServletRequest request) throws IOException {
		List<UserDTO> userDTOList = null;
		try {
			String userRole = request.getParameter("loggedInUserRole");
			String userId = request.getParameter("loggedInUserId");
			userDTOList = userService.getUserList(userRole, userId);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserControllerImpl-getUserList method: " + ex);
		}
		return userDTOList;
	}
	
	public ModelAndView viewList(ModelAndView model, HttpServletRequest request) throws IOException {
		try {
			model.setViewName("ViewList");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserControllerImpl-showUserList method: " + ex);
		}
		return model;
	}
	
	public @ResponseBody List<UserDTO> getViewList(HttpServletRequest request) throws IOException {
		List<UserDTO> userDTOList = null;
		try {
			userDTOList = userService.getViewList();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserControllerImpl-getUserList method: " + ex);
		}
		return userDTOList;
	}

	public @ResponseBody List<UnitDTO> getUnitList() throws IOException {
		List<UnitDTO> unitDTOList = null;
		try {
			unitDTOList = userService.getUnitList();
			UnitDTO unitDTO = new UnitDTO();
			unitDTO.setUnitId(0);
			unitDTO.setUnitName("Please select");
			unitDTOList.add(0, unitDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserControllerImpl-getUnitList method: " + ex);
		}
		return unitDTOList;
	}

	public ModelAndView addUser(ModelAndView model, HttpServletRequest request) {
		try {
			model.setViewName("UserForm");
			model.addObject("action", "add");
			if (request.getSession().getAttribute("loggedInUserRole") != null
					&& !"".equals(request.getSession().getAttribute("loggedInUserRole"))) {
				model.addObject("loggedInUserRole", request.getSession().getAttribute("loggedInUserRole"));
			} else {
				model.addObject("loggedInUserRole", "");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserControllerImpl-addUser method: " + ex);
		}
		return model;
	}

	public @ResponseBody UserDTO saveUser(@RequestBody UserDTO userDTO) {
		String status = null;
		try {
			status = userService.saveOrUpdateUser(userDTO);
			if (status != null && "0".equals(status)) {
				userDTO.setStatus("0");
			} else if (status != null && "1".equals(status)) {
				userDTO.setStatus("1");
			} else if (status != null && "2".equals(status)) {
				userDTO.setStatus("2");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserControllerImpl-saveUser method: " + ex);
		}
		return userDTO;
	}

	public ModelAndView editUser(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
			int userId = Integer.parseInt(request.getParameter("id"));
			model = new ModelAndView("UserForm");
			model.addObject("userId", userId);
			model.addObject("action", "edit");
			model.addObject("loggedInUserRole", request.getSession().getAttribute("loggedInUserRole"));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserControllerImpl-editUser method: " + ex);
		}
		return model;
	}

	public @ResponseBody UserDTO getUserById(@PathVariable int userId) {
		UserDTO userDTO = null;
		try {
			userDTO = userService.getUserById(userId);
			ModelAndView model = new ModelAndView("UserForm");
			model.addObject("userDTO", userDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserControllerImpl-getUserById method: " + ex);
		}
		return userDTO;
	}

	public @ResponseBody String deleteUser(HttpServletRequest request) {
		String status = null;
		try {
			int userId = Integer.parseInt(request.getParameter("id"));
			status = userService.deleteUser(userId);
			if (status != null && "0".equals(status)) {
				/* status = "User deleted successfully"; */
			} else if (status != null && "1".equals(status)) {
				/* status = "User not deleted - Error occured"; */
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserControllerImpl-deleteUser method: " + ex);
		}
		return status;
	}

	public ModelAndView logoutUser(ModelAndView model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			model.setViewName("login");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserControllerImpl-logoutUser method: " + ex);
		}
		return model;
	}

	/*
	 * @ExceptionHandler(value = NullPointerException.class) public String
	 * exceptionHandlerNull() { m.addAttribute("mssg",
	 * "Null Pointer Exception Occured"); return "NullPointerException"; }
	 */
}