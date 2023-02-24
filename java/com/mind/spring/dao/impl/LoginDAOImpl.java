package com.mind.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.mind.spring.dao.LoginDAO;
import com.mind.spring.model.Login;

@Repository
public class LoginDAOImpl implements LoginDAO {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Login validateUser(Login login) {
		String sql = null;
		try {
			sql = "SELECT USER_ID,USER_ROLE FROM USER_MST WHERE USERNAME = '" + login.getUsername() + "' AND PASSWORD = '" + login.getPassword() + "'";
			return jdbcTemplate.query(sql, new ResultSetExtractor<Login>() {

				public Login extractData(ResultSet rs) throws SQLException, DataAccessException {
					Login login = new Login();
					if (rs.next()) {
						login.setUserRole(rs.getString("USER_ROLE"));
						login.setUserId(rs.getString("USER_ID"));
						login.setStatus("success");
					}else{
						login.setStatus("invalid");
					}
					return login;
				}
			});
		} catch (Exception ex) {
			System.out.println("error occured- " + ex);
		}
		Login lg = new Login();
		lg.setStatus("error");
		return lg;
	}
}
