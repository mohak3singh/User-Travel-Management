package com.mind.spring.dto;

public class TravelRequestDTO {
	private int srNo;
	private int travelId;
	private String travelReqNo;
	private int unitId;
	private String unitName;
	private int travellerId;
	private String travellerName;
	private int travelMode;
	private String travelModeName;
	private String email;
	private String gender;
	private String telephone;
	private String address;
	private String dateOfBirth;
	private String travelFrom;
	private String travelTo;
	private String travelDate;
	private String reasonForTravel;
	private String status;

	public TravelRequestDTO() {
	}

	public TravelRequestDTO(int srNo,int travelId, String travelReqNo, int unitId, String unitName,
			int travellerId, String travellerName, int travelMode,String travelModeName, String email, String gender, String telephone,
			String address, String dateOfBirth, String travelFrom, String travelTo, String travelDate,
			String reasonForTravel, String status) {
		this.srNo = srNo;
		this.travelId = travelId;
		this.travelReqNo = travelReqNo;
		this.unitId = unitId;
		this.unitName = unitName;
		this.travellerId = travellerId;
		this.travellerName = travellerName;
		this.travelMode = travelMode;
		this.travelModeName = travelModeName;
		this.email = email;
		this.gender = gender;
		this.telephone = telephone;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.travelFrom = travelFrom;
		this.travelTo = travelTo;
		this.travelDate = travelDate;
		this.reasonForTravel = reasonForTravel;
		this.status = status;
	}

	public int getSrNo() {
		return srNo;
	}

	public String getTravelModeName() {
		return travelModeName;
	}

	public void setTravelModeName(String travelModeName) {
		this.travelModeName = travelModeName;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getTravellerName() {
		return travellerName;
	}

	public void setTravellerName(String travellerName) {
		this.travellerName = travellerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getTravelId() {
		return travelId;
	}

	public void setTravelId(int travelId) {
		this.travelId = travelId;
	}

	public String getTravelReqNo() {
		return travelReqNo;
	}

	public void setTravelReqNo(String travelReqNo) {
		this.travelReqNo = travelReqNo;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public int getTravellerId() {
		return travellerId;
	}

	public void setTravellerId(int travellerId) {
		this.travellerId = travellerId;
	}

	public int getTravelMode() {
		return travelMode;
	}

	public void setTravelMode(int travelMode) {
		this.travelMode = travelMode;
	}

	public String getTravelFrom() {
		return travelFrom;
	}

	public void setTravelFrom(String travelFrom) {
		this.travelFrom = travelFrom;
	}

	public String getTravelTo() {
		return travelTo;
	}

	public void setTravelTo(String travelTo) {
		this.travelTo = travelTo;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public String getReasonForTravel() {
		return reasonForTravel;
	}

	public void setReasonForTravel(String reasonForTravel) {
		this.reasonForTravel = reasonForTravel;
	}

}
