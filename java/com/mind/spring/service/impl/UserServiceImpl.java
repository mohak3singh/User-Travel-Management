package com.mind.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mind.spring.dao.UserDAO;
import com.mind.spring.dto.UnitDTO;
import com.mind.spring.dto.UserDTO;
import com.mind.spring.model.Unit;
import com.mind.spring.model.User;
import com.mind.spring.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	public List<UserDTO> getUserList(String userRole, String userId) {
		List<UserDTO> userDTOList = null;
		UserDTO userDTO = null;
		try {
			List<User> userList = userDAO.getUserList(userRole, userId);

			if (userList != null && !userList.isEmpty()) {
				userDTOList = new ArrayList<UserDTO>();
				for (User user : userList) {
					userDTO = new UserDTO();
					BeanUtils.copyProperties(user, userDTO);
					userDTOList.add(userDTO);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserServiceImpl-getUserList method: " + ex);
		}
		return userDTOList;
	}
	
	public List<UserDTO> getViewList() {
		List<UserDTO> userDTOList = null;
		UserDTO userDTO = null;
		try {
			List<User> userList = userDAO.getViewList();

			if (userList != null && !userList.isEmpty()) {
				userDTOList = new ArrayList<UserDTO>();
				for (User user : userList) {
					userDTO = new UserDTO();
					BeanUtils.copyProperties(user, userDTO);
					userDTOList.add(userDTO);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserServiceImpl-getViewList method: " + ex);
		}
		return userDTOList;
	}

	public List<UnitDTO> getUnitList() {
		List<UnitDTO> unitDTOList = null;
		UnitDTO unitDTO = null;
		try {
			List<Unit> unitList = userDAO.getUnitList();
			if (unitList != null && !unitList.isEmpty()) {
				 unitDTOList = new ArrayList<UnitDTO>();
				for (Unit unit : unitList) {
					unitDTO = new UnitDTO();
					BeanUtils.copyProperties(unit, unitDTO);
					unitDTOList.add(unitDTO);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserServiceImpl-getUnitList method: " + ex);
		}
		return unitDTOList;
	}

	public UserDTO getUserById(int userId) {
		UserDTO userDTO = null;
		try {
			User user = userDAO.getUserById(userId);
			if (user != null) {
				userDTO = new UserDTO();
				BeanUtils.copyProperties(user, userDTO);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserServiceImpl-getUserById method: " + ex);
		}
		return userDTO;
	}

	public String saveOrUpdateUser(UserDTO userDTO) {
		String status = null;
		try {
			User user = new User();
			BeanUtils.copyProperties(userDTO, user);
			status = userDAO.saveOrUpdateUser(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserServiceImpl-saveOrUpdateUser method: " + ex);
		}
		return status;
	}

	public String deleteUser(int userId) {
		String status = null;
		try {
			status = userDAO.deleteUser(userId);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserServiceImpl-deleteUser method: " + ex);
		}
		return status;
	}
}