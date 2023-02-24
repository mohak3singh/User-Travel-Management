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

import com.mind.spring.dao.UserDAO;
import com.mind.spring.model.Unit;
import com.mind.spring.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<User> getUserList(String userRole, String userId) {

		List<User> listUser = null;

		try {
			String sql = null;

			/*if (userRole.equals("AD")) {*/
				sql = "SELECT ROW_NUMBER() OVER(ORDER BY (SELECT 1)) AS SR_NO, USRM.USER_ID, USRM.NAME, USRM.EMAIL, USRM.ADDRESS, USRM.TELEPHONE, "
						+ " DATE_OF_BIRTH, USRM.USER_ROLE, UNTM.UNIT_NAME FROM USER_MST USRM INNER JOIN UNIT_MST UNTM ON "
						+ "USRM.UNIT_ID = UNTM.UNIT_ID WHERE USRM.STATUS_ID = 10 ";
			 /*else if (userRole.equals("LA")) {
				sql = "SELECT ROW_NUMBER() OVER(ORDER BY (SELECT 1)) AS SR_NO, USRM.USER_ID, USRM.NAME, USRM.EMAIL, USRM.ADDRESS, USRM.TELEPHONE, "
						+ "  FORMAT(DATE_OF_BIRTH,'dd-MM-yyyy') AS DATE_OF_BIRTH, USRM.USER_ROLE, UNTM.UNIT_NAME FROM USER_MST USRM INNER JOIN UNIT_MST UNTM ON "
						+ "USRM.UNIT_ID = UNTM.UNIT_ID WHERE USRM.USER_ROLE <> 'AD' AND USRM.UNIT_ID = (SELECT UNIT_ID from USER_MST WHERE USER_ID = "
						+ userId + ") AND USRM.STATUS_ID = 10 " ;
			} else {
				sql = "SELECT ROW_NUMBER() OVER(ORDER BY (SELECT 1)) AS SR_NO, USRM.USER_ID, USRM.NAME, USRM.EMAIL, USRM.ADDRESS, USRM.TELEPHONE, "
						+ "  FORMAT(DATE_OF_BIRTH,'dd-MM-yyyy') AS DATE_OF_BIRTH, USRM.USER_ROLE, UNTM.UNIT_NAME FROM USER_MST USRM INNER JOIN UNIT_MST UNTM ON "
						+ "USRM.UNIT_ID = UNTM.UNIT_ID WHERE USER_ID = " + userId + " AND USRM.STATUS_ID = 10 ";
			}*/

			listUser = new ArrayList<User>();

			listUser = jdbcTemplate.query(sql, new RowMapper<User>() {

				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User aUser = new User();
					aUser.setId(rs.getInt("USER_ID"));
					aUser.setSrNo(rs.getInt("SR_NO"));
					aUser.setName(rs.getString("NAME"));
					aUser.setEmail(rs.getString("EMAIL"));
					aUser.setAddress(rs.getString("ADDRESS"));
					aUser.setTelephone(rs.getString("TELEPHONE"));
					aUser.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
					if ("AD".equals(rs.getString("USER_ROLE"))) {
						aUser.setUserRole("Admin");
					} else if ("LA".equals(rs.getString("USER_ROLE"))) {
						aUser.setUserRole("Local Admin");
					} else {
						aUser.setUserRole(rs.getString("USER_ROLE"));
					}
					aUser.setUnitName(rs.getString("UNIT_NAME"));
					return aUser;
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserDAOImpl- getUserList method: " + ex);
		}
		return listUser;
	}

	public List<User> getViewList() {

		List<User> listUser = null;

		try {
			listUser = new ArrayList<User>();
			User user1 = new User(0, 1, "Mohak01", "Mohak01@gmail.com", "abc", "3333333333", "1", "1", "unit-01", "",
					"", "Unit-01");
			User user2 = new User(0, 2, "Mohak02", "Mohak02@gmail.com", "abc", "3333333333", "1", "2", "unit-01", "",
					"", "Unit-01");
			User user3 = new User(0, 3, "Mohak02", "Mohak03@gmail.com", "abc", "3333333333", "1", "3", "unit-01", "",
					"", "Unit-01");
			User user4 = new User(0, 4, "Mohak04", "Mohak04@gmail.com", "abc", "3333333333", "1", "4", "unit-01", "",
					"", "Unit-01");
			User user5 = new User(0, 5, "Mohak05", "Mohak05@gmail.com", "abc", "3333333333", "1", "5", "unit-01", "",
					"", "Unit-01");
			User user6 = new User(0, 6, "Mohak06", "Mohak06@gmail.com", "abc", "3333333333", "1", "6", "unit-01", "",
					"", "Unit-01");
			User user7 = new User(0, 7, "Mohak07", "Mohak07@gmail.com", "abc", "3333333333", "1", "7", "unit-01", "",
					"", "Unit-01");
			User user8 = new User(0, 8, "Mohak08", "Mohak08@gmail.com", "abc", "3333333333", "1", "8", "unit-01", "",
					"", "Unit-01");
			User user9 = new User(0, 9, "Mohak09", "Mohak09@gmail.com", "abc", "3333333333", "1", "9", "unit-01", "",
					"", "Unit-01");
			User user10 = new User(0, 10, "Mohak10", "Mohak10@gmail.com", "abc", "3333333333", "1", "10", "unit-01", "",
					"", "Unit-01");

			listUser.add(user1);
			listUser.add(user2);
			listUser.add(user3);
			listUser.add(user4);
			listUser.add(user5);
			listUser.add(user6);
			listUser.add(user7);
			listUser.add(user8);
			listUser.add(user9);
			listUser.add(user10);
			/* listUser.add(user11); */

			return listUser;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserDAOImpl- viewList method: " + ex);
		}
		return listUser;
	}

	public List<Unit> getUnitList() {

		List<Unit> listUnit = null;

		try {
			String sql = "SELECT UNIT_ID, UNIT_NAME from UNIT_MST";

			listUnit = new ArrayList<Unit>();
			listUnit = jdbcTemplate.query(sql, new RowMapper<Unit>() {

				public Unit mapRow(ResultSet rs, int rowNum) throws SQLException {
					Unit aUnit = new Unit();
					aUnit.setUnitId(rs.getInt("UNIT_ID"));
					aUnit.setUnitName(rs.getString("UNIT_NAME"));
					return aUnit;
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserDAOImpl- getUnitList method: " + ex);
		}
		return listUnit;
	}

	public User getUserById(int userId) {
		try {
			String sql = "SELECT USER_ID, NAME, EMAIL, ADDRESS, TELEPHONE, USERNAME, PASSWORD,DATE_OF_BIRTH, USER_ROLE, GENDER, UNIT_ID FROM USER_MST WHERE USER_ID="
					+ userId + " AND STATUS_ID = 10 ";
			return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {

				public User extractData(ResultSet rs) throws SQLException, DataAccessException {

					if (rs.next()) {
						User user = new User();
						user.setId(rs.getInt("USER_ID"));
						user.setName(rs.getString("NAME"));
						user.setEmail(rs.getString("EMAIL"));
						user.setAddress(rs.getString("ADDRESS"));
						user.setTelephone(rs.getString("TELEPHONE"));
						user.setUsername(rs.getString("USERNAME"));
						user.setPassword(rs.getString("PASSWORD"));
						user.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
						user.setUserRole(rs.getString("USER_ROLE"));
						user.setGender(rs.getString("GENDER"));
						user.setUnitId(rs.getString("UNIT_ID"));
						return user;
					}
					return null;
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserDAOImpl- getUserById method: " + ex);
		}
		return null;
	}

	public String saveOrUpdateUser(User user) {
		String status = null;
		String sql = null;
		/*String dateOfBirth = "";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date dob = new Date();
		Date currentDate = new Date();

		dob = formatter.parse(user.getDateOfBirth());*/
		//System.out.println("currentDate- " + sdf.format(currentDate));
		
		String dob = user.getDateOfBirth();
		try {
			if (user.getId() > 0) {
				// update
				sql = "UPDATE USER_MST SET NAME=?, EMAIL=?, ADDRESS=?, TELEPHONE=?, USERNAME=?, PASSWORD=?, DATE_OF_BIRTH=?, USER_ROLE=?, GENDER=?, UNIT_ID=?, STATUS_ID=? WHERE USER_ID=?";
				jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getAddress(), user.getTelephone(),
						user.getUsername(), user.getPassword(),dob, user.getUserRole(), user.getGender(),
						user.getUnitId(), 10, user.getId());
				status = "2";
			} else {
				sql = "SELECT USERNAME FROM USER_MST WHERE USERNAME = '" + user.getUsername() + "'";
				String userNameExist = validateUsername(sql);

				if (userNameExist != null && "true".equals(userNameExist)) {
					status = "0";
				} else {
					// insert
					sql = "INSERT INTO USER_MST (NAME, EMAIL, ADDRESS, TELEPHONE, USERNAME, PASSWORD, DATE_OF_BIRTH, USER_ROLE, GENDER, UNIT_ID, STATUS_ID)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getAddress(), user.getTelephone(),
							user.getUsername(), user.getPassword(), dob, user.getUserRole(),
							user.getGender(), user.getUnitId(), 10);
					status = "1";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserDAOImpl- saveOrUpdateUser method: " + ex);
		}
		return status;
	}

	public String deleteUser(int userId) {
		String status = null;

		try {
			String sql = "UPDATE USER_MST SET STATUS_ID = 30 WHERE USER_ID=?";
			jdbcTemplate.update(sql, userId);

			String sqlConfirmDelete = "SELECT * FROM USER_MST WHERE USER_ID = " + userId + " AND STATUS_ID = 10 ";

			String validateDeleteUser = validateConfirmDelete(sqlConfirmDelete);

			if (validateDeleteUser != null && "true".equals(validateDeleteUser)) {
				status = "0";
			} else if (validateDeleteUser != null && "false".equals(validateDeleteUser)) {
				status = "1";
			} else {
				status = "1";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured in UserDAOImpl- deleteUser method: " + ex);
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
					System.out.println("Error occured in UserDAOImpl- validateConfirmDelete method: " + ex);
				}
				return "true";
			}
		});
	}

	public String validateUsername(String sql) {

		return jdbcTemplate.query(sql, new ResultSetExtractor<String>() {

			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				try {
					if (rs.next()) {
						return "true";
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("Error occured in UserDAOImpl- validateUsername method: " + ex);
				}
				return "false";
			}
		});
	}
}
