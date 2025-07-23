package org.moviefusion.service;


import org.moviefusion.model.Profile;
import org.moviefusion.model.UserLogin;
import org.moviefusion.model.UserMaster;

public interface UserMasterService {

	boolean isRegisterUser(UserMaster userMaster);
	
	Profile getUserProfile(int userId);
	
	public Profile loginAndFetchUser(UserLogin login);
	
	boolean updateUserProfile(int userId, UserMaster updatedUser);
}
 