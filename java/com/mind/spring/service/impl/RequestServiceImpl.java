package com.mind.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mind.spring.dao.RequestDAO;
import com.mind.spring.dto.TravelRequestDTO;
import com.mind.spring.dto.UserDTO;
import com.mind.spring.model.TravelRequest;
import com.mind.spring.model.User;
import com.mind.spring.service.RequestService;

@Component
public class RequestServiceImpl implements RequestService {

	@Autowired
	RequestDAO requestDAO;

	public TravelRequestDTO getTravellerInfoById(int userId) {
		TravelRequestDTO travelRequestDTO = null;
		try {
			TravelRequest travelRequest = requestDAO.getTravellerInfoById(userId);
			if (travelRequest != null) {
				travelRequestDTO = new TravelRequestDTO();
				BeanUtils.copyProperties(travelRequest, travelRequestDTO);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestServiceImpl-getTravellerInfoById method: " + ex);
		}
		return travelRequestDTO;
	}

	public String saveOrUpdateTravelRequest(TravelRequestDTO travelRequestDTO) {
		String status = null;
		try {
			TravelRequest travelRequest = new TravelRequest();
			BeanUtils.copyProperties(travelRequestDTO, travelRequest);
			status = requestDAO.saveOrUpdateTravelRequest(travelRequest);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestServiceImpl-saveOrUpdateTravelRequest method: " + ex);
		}
		return status;
	}

	public List<TravelRequestDTO> getTravelRequestList(String userRole, String travellerId) {
		List<TravelRequestDTO> requestDTOList = null;
		TravelRequestDTO travelRequestDTO = null;
		try {
			List<TravelRequest> requestList = requestDAO.getTravelRequestList(userRole, travellerId);

			if (requestList != null && !requestList.isEmpty()) {
				requestDTOList = new ArrayList<TravelRequestDTO>();
				for (TravelRequest travelRequest : requestList) {
					travelRequestDTO = new TravelRequestDTO();
					BeanUtils.copyProperties(travelRequest, travelRequestDTO);
					requestDTOList.add(travelRequestDTO);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestServiceImpl-getTravelRequestList method: " + ex);
		}
		return requestDTOList;
	}

	public TravelRequestDTO getRequestDetailForEdit(int travellerId, int travelId, int unitId) {
		TravelRequestDTO travelRequestDTO = null;
		try {
			TravelRequest travelRequest = requestDAO.getRequestDetailForEdit(travellerId, travelId, unitId);
			if (travelRequest != null) {
				travelRequestDTO = new TravelRequestDTO();
				BeanUtils.copyProperties(travelRequest, travelRequestDTO);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestServiceImpl-getAllRequestListInfo method: " + ex);
		}
		return travelRequestDTO;
	}

	public String deleteRequestListUser(String travellerId, String travelId, String unitId) {
		String status = null;
		try {
			status = requestDAO.deleteRequestListUser(travellerId, travelId, unitId);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestServiceImpl-deleteRequestListUser method: " + ex);
		}
		return status;
	}

}
