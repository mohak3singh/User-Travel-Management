package com.mind.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mind.spring.dao.RequestDAO;
import com.mind.spring.model.TravelRequest;

@Repository
public class RequestDAOImpl implements RequestDAO {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public TravelRequest getTravellerInfoById(int userId) {
		try {
			String sql = "SELECT USRM.USER_ID, USRM.NAME, USRM.EMAIL, USRM.ADDRESS, USRM.TELEPHONE, "
					+ " "
					+ "DATE_OF_BIRTH, USRM.GENDER,USRM.UNIT_ID, UNTM.UNIT_NAME FROM USER_MST USRM INNER JOIN UNIT_MST UNTM ON "
					+ "USRM.UNIT_ID = UNTM.UNIT_ID WHERE USER_ID =" + userId + " AND USRM.STATUS_ID = 10 ";

			return jdbcTemplate.query(sql, new ResultSetExtractor<TravelRequest>() {

				public TravelRequest extractData(ResultSet rs) throws SQLException, DataAccessException {

					if (rs.next()) {
						TravelRequest travelRequest = new TravelRequest();
						travelRequest.setTravellerId(rs.getInt("USER_ID"));
						travelRequest.setUnitName(rs.getString("UNIT_NAME"));
						travelRequest.setTravellerName(rs.getString("NAME"));
						travelRequest.setEmail(rs.getString("EMAIL"));
						if ("M".equals(rs.getString("GENDER"))) {
							travelRequest.setGender("Male");
						} else {
							travelRequest.setGender("Female");
						}
						travelRequest.setTelephone(rs.getString("TELEPHONE"));
						travelRequest.setAddress(rs.getString("ADDRESS"));
						travelRequest.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
						travelRequest.setUnitId(rs.getInt("UNIT_ID"));
						return travelRequest;
					}
					return null;
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestDAOImpl- getTravellerInfoById method: " + ex);
		}
		return null;
	}

	public String saveOrUpdateTravelRequest(TravelRequest travelRequest) {
		String status = null;
		String sql = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date currentDate = new Date();
		Date travelDate = new Date();

		try {
			travelDate = formatter.parse(travelRequest.getTravelDate());
			System.out.println("currentDate- " + sdf.format(currentDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (travelRequest.getTravelId() > 0) {
				// update
				sql = "UPDATE T_TRAVEL_DETAIL SET TRAVEL_MODE=?, TRAVEL_FROM=?, TRAVEL_TO=?, TRAVEL_DATE=?, REASON_FOR_TRAVEL=?, U_USERID=?, U_DATETIME=?, STATUS_ID=? WHERE TRAVEL_ID=? AND UNIT_ID=? AND TRAVELLER_ID=?";
				jdbcTemplate.update(sql, travelRequest.getTravelMode(), travelRequest.getTravelFrom(),
						travelRequest.getTravelTo(), sdf.format(travelDate), travelRequest.getReasonForTravel(),
						travelRequest.getTravellerId(), sdf.format(currentDate), 10, travelRequest.getTravelId(),
						travelRequest.getUnitId(), travelRequest.getTravellerId());
				status = "2";
			} else {
				// insert
				sql = "INSERT INTO T_TRAVEL_DETAIL (UNIT_ID,TRAVELLER_ID, TRAVEL_MODE, TRAVEL_FROM, TRAVEL_TO, TRAVEL_DATE,REASON_FOR_TRAVEL, C_USERID, C_DATETIME, STATUS_ID)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				jdbcTemplate.update(sql, travelRequest.getUnitId(), travelRequest.getTravellerId(),
						travelRequest.getTravelMode(), travelRequest.getTravelFrom(), travelRequest.getTravelTo(),
						sdf.format(travelDate), travelRequest.getReasonForTravel(), travelRequest.getTravellerId(),
						sdf.format(currentDate), 10);
				status = "1";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestDAOImpl- saveOrUpdateTravelRequest method: " + ex);
		}
		return status;
	}

	public List<TravelRequest> getTravelRequestList(String userRole, String travellerId) {

		List<TravelRequest> requestTravelList = null;

		try {
			String sql = null;

			/*if (userRole.equals("AD")) {*/
				sql = "SELECT ROW_NUMBER() OVER(ORDER BY (SELECT 1)) AS SR_NO, TRD.TRAVEL_ID, TRD.UNIT_ID, UNTM.UNIT_NAME, TRD.TRAVELLER_ID, USRM.NAME AS TRAVELLER_NAME, USRM.EMAIL AS EMAIL, "
						+ "USRM.DATE_OF_BIRTH, USRM.TELEPHONE AS TELEPHONE, TRD.TRAVEL_MODE, TRD.TRAVEL_FROM, TRD.TRAVEL_TO, "
						+ "TRD.TRAVEL_DATE,TRD.REASON_FOR_TRAVEL FROM T_TRAVEL_DETAIL TRD INNER JOIN USER_MST USRM ON TRD.UNIT_ID = USRM.UNIT_ID AND "
						+ "TRD.TRAVELLER_ID = USRM.USER_ID INNER JOIN UNIT_MST UNTM ON USRM.UNIT_ID = UNTM.UNIT_ID WHERE TRD.STATUS_ID = 10";
			/*} else if (userRole.equals("LA")) {
				sql = "SELECT ROW_NUMBER() OVER(ORDER BY (SELECT 1)) AS SR_NO, TRD.TRAVEL_ID, TRD.UNIT_ID, UNTM.UNIT_NAME, TRD.TRAVELLER_ID, USRM.NAME AS TRAVELLER_NAME, USRM.EMAIL AS EMAIL, "
						+ "FORMAT(USRM.DATE_OF_BIRTH,'dd-MM-yyyy') AS DATE_OF_BIRTH, USRM.TELEPHONE AS TELEPHONE, TRD.TRAVEL_MODE, TRD.TRAVEL_FROM, TRD.TRAVEL_TO, "
						+ "FORMAT(TRD.TRAVEL_DATE,'dd-MM-yyyy') AS TRAVEL_DATE,TRD.REASON_FOR_TRAVEL FROM T_TRAVEL_DETAIL TRD INNER JOIN USER_MST USRM ON TRD.UNIT_ID = USRM.UNIT_ID AND "
						+ "TRD.TRAVELLER_ID = USRM.USER_ID INNER JOIN UNIT_MST UNTM ON USRM.UNIT_ID = UNTM.UNIT_ID WHERE USRM.USER_ROLE <> 'AD' AND TRD.UNIT_ID = (SELECT UNIT_ID from TRAVEL_REQUEST_DETAIL WHERE TRAVELLER_ID = "
						+ travellerId + " AND TRD.STATUS_ID = 10 ";
			} else {

				sql = "SELECT ROW_NUMBER() OVER(ORDER BY (SELECT 1)) AS SR_NO, TRD.TRAVEL_ID, TRD.UNIT_ID, UNTM.UNIT_NAME, TRD.TRAVELLER_ID, USRM.NAME AS TRAVELLER_NAME, USRM.EMAIL AS EMAIL, "
						+ "FORMAT(USRM.DATE_OF_BIRTH,'dd-MM-yyyy') AS DATE_OF_BIRTH, USRM.TELEPHONE AS TELEPHONE, TRD.TRAVEL_MODE, TRD.TRAVEL_FROM, TRD.TRAVEL_TO, "
						+ "FORMAT(TRD.TRAVEL_DATE,'dd-MM-yyyy') AS TRAVEL_DATE,TRD.REASON_FOR_TRAVEL FROM T_TRAVEL_DETAIL TRD INNER JOIN USER_MST USRM ON TRD.UNIT_ID = USRM.UNIT_ID AND "
						+ "TRD.TRAVELLER_ID = USRM.USER_ID INNER JOIN UNIT_MST UNTM ON USRM.UNIT_ID = UNTM.UNIT_ID WHERE TRAVELLER_ID =" + travellerId + " AND TRD.STATUS_ID = 10 ";
			}*/

			requestTravelList = new ArrayList<TravelRequest>();

			requestTravelList = jdbcTemplate.query(sql, new RowMapper<TravelRequest>() {

				public TravelRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
					TravelRequest travelList = new TravelRequest();

					travelList.setSrNo(rs.getInt("SR_NO"));
					travelList.setTravelId(rs.getInt("TRAVEL_ID"));
					travelList.setTravelReqNo(rs.getString("UNIT_NAME")+"/"+rs.getInt("TRAVEL_ID"));
					travelList.setUnitId(rs.getInt("UNIT_ID"));
					travelList.setUnitName(rs.getString("UNIT_NAME"));
					travelList.setTravellerId(rs.getInt("TRAVELLER_ID"));
					travelList.setTravellerName(rs.getString("TRAVELLER_NAME"));
					travelList.setEmail(rs.getString("EMAIL"));
					travelList.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
					travelList.setTelephone(rs.getString("TELEPHONE"));

					if (rs.getInt("TRAVEL_MODE") == 1) {
						travelList.setTravelModeName("Flight");
					} else {
						travelList.setTravelModeName("Train");
					}
					/* travelList.setTravelMode(rs.getInt("TRAVEL_MODE")); */
					travelList.setTravelFrom(rs.getString("TRAVEL_FROM"));
					travelList.setTravelTo(rs.getString("TRAVEL_TO"));
					travelList.setTravelDate(rs.getString("TRAVEL_DATE"));
					travelList.setReasonForTravel(rs.getString("REASON_FOR_TRAVEL"));
					return travelList;
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestDAOImpl- getTravelRequestList method: " + ex);
		}
		return requestTravelList;
	}

	public TravelRequest getRequestDetailForEdit(int travellerId, int travelId, int unitId) {
		try {
			String sql = "SELECT TRD.TRAVEL_ID, TRD.UNIT_ID, UNTM.UNIT_NAME, TRD.TRAVELLER_ID, USRM.NAME AS TRAVELLER_NAME, USRM.EMAIL AS EMAIL, "
					+ "USRM.DATE_OF_BIRTH, USRM.GENDER AS GENDER, USRM.TELEPHONE AS TELEPHONE, USRM.ADDRESS AS ADDRESS, TRD.TRAVEL_MODE, TRD.TRAVEL_FROM, TRD.TRAVEL_TO, "
					+ "TRD.TRAVEL_DATE, TRD.REASON_FOR_TRAVEL FROM T_TRAVEL_DETAIL TRD INNER JOIN USER_MST USRM ON TRD.UNIT_ID = USRM.UNIT_ID AND "
					+ "TRD.TRAVELLER_ID = USRM.USER_ID INNER JOIN UNIT_MST UNTM ON USRM.UNIT_ID = UNTM.UNIT_ID WHERE TRD.TRAVELLER_ID = "
					+ travellerId + " AND TRD.UNIT_ID = " + unitId + " AND TRD.TRAVEL_ID = " + travelId + " AND TRD.STATUS_ID = 10 ";

			return jdbcTemplate.query(sql, new ResultSetExtractor<TravelRequest>() {

				public TravelRequest extractData(ResultSet rs) throws SQLException, DataAccessException {

					if (rs.next()) {
						TravelRequest travelRequest = new TravelRequest();
						travelRequest.setTravellerId(rs.getInt("TRAVELLER_ID"));
						travelRequest.setTravelId(rs.getInt("TRAVEL_ID"));
						travelRequest.setTravelReqNo(rs.getString("UNIT_NAME")+"/"+rs.getInt("TRAVEL_ID"));
						travelRequest.setUnitName(rs.getString("UNIT_NAME"));
						travelRequest.setUnitId(rs.getInt("UNIT_ID"));
						travelRequest.setTravellerName(rs.getString("TRAVELLER_NAME"));
						travelRequest.setEmail(rs.getString("EMAIL"));
						if ("M".equals(rs.getString("GENDER"))) {
							travelRequest.setGender("Male");
						} else {
							travelRequest.setGender("Female");
						}
						travelRequest.setTelephone(rs.getString("TELEPHONE"));
						travelRequest.setAddress(rs.getString("ADDRESS"));
						travelRequest.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
						travelRequest.setTravelMode(rs.getInt("TRAVEL_MODE"));
						if (rs.getInt("TRAVEL_MODE") == 1) {
							travelRequest.setTravelModeName("Flight");
						} else {
							travelRequest.setTravelModeName("Train");
						}
						travelRequest.setTravelFrom(rs.getString("TRAVEL_FROM"));
						travelRequest.setTravelTo(rs.getString("TRAVEL_TO"));
						travelRequest.setTravelDate(rs.getString("TRAVEL_DATE"));
						travelRequest.setReasonForTravel(rs.getString("REASON_FOR_TRAVEL"));
						return travelRequest;
					}
					return null;
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestDAOImpl- getRequestDetailForEdit method: " + ex);
		}
		return null;
	}
	
	public String deleteRequestListUser(String travellerId, String travelId, String unitId) {
		String status = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date currentDate = new Date();
		
		try {
			String sql = "UPDATE T_TRAVEL_DETAIL SET U_USERID = ? , U_DATETIME = ? , STATUS_ID = ? WHERE TRAVELLER_ID=? AND TRAVEL_ID=? AND UNIT_ID=? ";
			jdbcTemplate.update(sql, travellerId, sdf.format(currentDate), 30, travellerId, travelId, unitId);

			String sqlConfirmDelete = "SELECT * FROM T_TRAVEL_DETAIL WHERE TRAVELLER_ID = " + travellerId + " AND TRAVEL_ID = " + travelId + " AND UNIT_ID = " + unitId + " AND STATUS_ID = 10" ;

			String validateDeleteListUser = validateConfirmDelete(sqlConfirmDelete);

			if (validateDeleteListUser != null && "true".equals(validateDeleteListUser)) {
				status = "0";
			} else if (validateDeleteListUser != null && "false".equals(validateDeleteListUser)) {
				status = "1";
			} else {
				status = "1";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in RequestDAOImpl- deleteRequestListUser method: " + ex);
		}
		return status;
	}

	public String validateConfirmDelete(String sqlDelete) {

		return jdbcTemplate.query(sqlDelete, new ResultSetExtractor<String>() {

			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				try {
					if (rs.next()) {
						return "false";
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("Error occured in RequestDAOImpl- validateConfirmDelete method: " + ex);
				}
				return "true";
			}
		});
	}
}
