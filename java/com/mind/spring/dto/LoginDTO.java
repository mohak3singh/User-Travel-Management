package com.mind.spring.dto;

public class LoginDTO {
	private String username;
	private String password;
	private String userId;
	private String userRole;
	private String status;

	public LoginDTO() {
	}

	public LoginDTO(String username, String password,String userId, String userRole, String status) {

		this.username = username;
		this.password = password;
		this.userId = userId;
		this.userRole = userRole;
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
