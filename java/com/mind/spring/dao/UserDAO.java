package com.mind.spring.dao;

import java.util.List;

import com.mind.spring.model.Unit;
import com.mind.spring.model.User;

public interface UserDAO {

	public List<User> getUserList(String userRole, String userId);
	
	public List<User> getViewList();
	
	public List<Unit> getUnitList();

	public User getUserById(int userId);

	public String saveOrUpdateUser(User user);

	public String deleteUser(int userId);

}