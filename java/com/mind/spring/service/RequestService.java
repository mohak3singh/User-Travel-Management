package com.mind.spring.service;

import java.util.List;

import com.mind.spring.dto.TravelRequestDTO;
import com.mind.spring.dto.UserDTO;

public interface RequestService {

	public TravelRequestDTO getTravellerInfoById(int userId);
	
	public String saveOrUpdateTravelRequest(TravelRequestDTO travelRequestDTO);
	
	public List<TravelRequestDTO> getTravelRequestList(String userRole, String travellerId);

	public TravelRequestDTO getRequestDetailForEdit(int travellerId, int travelId, int unitId);
	
	public String deleteRequestListUser(String travellerId, String travelId, String unitId);
}
