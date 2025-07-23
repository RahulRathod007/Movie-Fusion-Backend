package org.moviefusion.service;


import org.moviefusion.model.Profile;
import org.moviefusion.model.UserLogin;
import org.moviefusion.model.UserMaster;
import org.moviefusion.repository.UserMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMasterServiceImpl implements UserMasterService{

	@Autowired
	UserMasterRepository userMasterRepo;
	
	@Override
	public boolean isRegisterUser(UserMaster userMaster) {
		return userMasterRepo.isRegisterUser(userMaster);
	}

	@Override
	public Profile getUserProfile(int userId) {
		return userMasterRepo.getUserProfile(userId);
	}
	
	@Override
	public Profile loginAndFetchUser(UserLogin login) {
		return userMasterRepo.loginAndFetchUser(login);
	}
	
	@Override
	public boolean updateUserProfile(int userId, UserMaster updatedUser) {
		return userMasterRepo.updateUserProfile(userId, updatedUser);
	}


	
 
}
