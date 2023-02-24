package com.mind.spring.dto;

public class UserDTO {
	private int id;
	private int srNo;
	private String name;
	private String email;
	private String address;
	private String telephone;
	private String username;
	private String password;
	private String dateOfBirth;
	private String userRole;
	private String gender;
	private String unitId;
	private String unitName;
	private String status;

	public UserDTO() {
	}

	public UserDTO(int id, int srNo, String name, String email, String address, String telephone, String status,
			String dateOfBirth, String userRole, String gender, String unitId, String unitName) {
		this.id = id;
		this.srNo = srNo;
		this.name = name;
		this.email = email;
		this.address = address;
		this.telephone = telephone;
		this.status = status;
		this.dateOfBirth = dateOfBirth;
		this.userRole = userRole;
		this.gender = gender;
		this.unitId = unitId;
		this.unitName = unitName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsername() {
		return username;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
