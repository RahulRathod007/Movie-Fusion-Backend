package org.moviefusion.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.moviefusion.model.AdminLogin;
import org.moviefusion.model.AdminMaster;
import org.moviefusion.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository
public class AdminMasterRepositoryImpl implements AdminMasterRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean isRegisterAdmin(AdminMaster adminmaster) {
		
		int value = jdbcTemplate.update("insert into admin_master(admin_name, email, password) values (?, ?, ?)", new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, adminmaster.getAdmin_name());
				ps.setString(2, adminmaster.getEmail());
				ps.setString(3, adminmaster.getPassword());	
			}});
		return value>0;
	}

	@Override
	public Profile loginAndFetchAdmin(AdminLogin login) {
	    String sql = "SELECT admin_id, admin_name, email FROM Admin_Master WHERE email = ? AND password = ?";
	    try {
	        return jdbcTemplate.queryForObject(
	            sql,
	            (rs, rowNum) -> new Profile(
	                rs.getInt("admin_id"),
	                rs.getString("admin_name"),
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
	public Profile getAdminProfile(int adminId) {
	    String sql = "SELECT admin_id, admin_name, email FROM Admin_Master WHERE admin_id = ?";
	    try {
	        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> { 
	            Profile profile = new Profile();
	            profile.setUser_id(rs.getInt("admin_id"));
	            profile.setUser_name(rs.getString("admin_name"));
	            profile.setEmail(rs.getString("email"));
	            return profile;
	        }, adminId);
	    } catch (EmptyResultDataAccessException e) {
	        return null; // No admin found for given adminId
	    }
	}


	@Override
	public Boolean updateAdminProfile(int adminId, AdminMaster updatedAdmin) {
		String sql = "UPDATE Admin_Master SET admin_name = ? , email= ?  where admin_id = ?";
		
		int value = jdbcTemplate.update(sql, updatedAdmin.getAdmin_name(), updatedAdmin.getEmail(), adminId);
		return value> 0;
	}

	 

}
