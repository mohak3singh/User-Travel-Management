package com.mind.spring.service;

import java.util.List;

import com.mind.spring.dto.UnitDTO;
import com.mind.spring.dto.UserDTO;

public interface UserService {

	public List<UserDTO> getUserList(String userRole, String userId);
	
	public List<UserDTO> getViewList();
	
	public List<UnitDTO> getUnitList();

	public UserDTO getUserById(int userId);

	public String saveOrUpdateUser(UserDTO userDTO);

	public String deleteUser(int userId);

}
