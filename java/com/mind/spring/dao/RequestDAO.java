package com.mind.spring.dao;

import java.util.List;

import com.mind.spring.model.TravelRequest;
import com.mind.spring.model.User;

public interface RequestDAO {

	public TravelRequest getTravellerInfoById(int userId);
	
	public String saveOrUpdateTravelRequest(TravelRequest travelRequest);
	
	public List<TravelRequest> getTravelRequestList(String userRole, String travellerId);
	
	public TravelRequest getRequestDetailForEdit(int travellerId, int travelId, int unitId);
	
	public String deleteRequestListUser(String travellerId, String travelId, String unitId);
}
