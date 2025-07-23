package org.moviefusion.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.moviefusion.model.Profile;
import org.moviefusion.model.UserLogin;
import org.moviefusion.model.UserMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository
public class UserMasterRepositoryImpl implements UserMasterRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	List<UserLogin> list;
	 
	@Override
	public boolean isRegisterUser(UserMaster userMaster) {
		
		int value = jdbcTemplate.update("insert into user_master(user_name, email, password) values (?, ?, ?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, userMaster.getUser_name());
				ps.setString(2, userMaster.getEmail());
				ps.setString(3, userMaster.getPassword());
			}}); 
		
		return value > 0;
	}


	@Override
	public Profile getUserProfile(int userId) {
	    String sql = "SELECT user_id, user_name, email FROM User_Master WHERE user_id = ?";
	    try {
	        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
	            Profile profile = new Profile();
	            profile.setUser_id(rs.getInt("user_id"));
	            profile.setUser_name(rs.getString("user_name"));
	            profile.setEmail(rs.getString("email"));
	            return profile;
	        }, userId);
	    } catch (EmptyResultDataAccessException e) {
	        return null; // No user found
	    }
	}


	@Override
	public Profile loginAndFetchUser(UserLogin login) {
	    String sql = "SELECT user_id, user_name, email FROM User_Master WHERE email = ? AND password = ?";
	    try {
	        return jdbcTemplate.queryForObject(
	            sql,
	            (rs, rowNum) -> new Profile(
	                rs.getInt("user_id"),
	                rs.getString("user_name"),
	                rs.getString("email")
	            ),
	            login.getEmail(),
	            login.getPassword()
	        );
	    } catch (EmptyResultDataAccessException e) {
	        return null; // user not found
	    }
	}

	@Override
	public boolean updateUserProfile(int userId, UserMaster updatedUser) {
		System.out.println("Updating user: " + userId + " with name: " + updatedUser.getUser_name() + " and email: " + updatedUser.getEmail());
		
		 String sql = "UPDATE User_Master SET user_name = ?, email = ? WHERE user_id = ?";

	        int value = jdbcTemplate.update(sql,
	            updatedUser.getUser_name(), 
	            updatedUser.getEmail(), 	      
	            userId
	        );
		return value> 0;
	}

}
