package org.moviefusion.service;

import org.moviefusion.model.AdminLogin;
import org.moviefusion.model.AdminMaster;
import org.moviefusion.model.Profile;
import org.moviefusion.repository.AdminMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminMasterServiceImpl  implements AdminMasterService{
	
	@Autowired
	AdminMasterRepository adminMasterRepo;
	
	@Override
	public boolean isRegisterAdmin(AdminMaster adminmaster) {
		return adminMasterRepo.isRegisterAdmin(adminmaster);
	}

	@Override
	public Profile loginAndFetchAdmin(AdminLogin login) {
		return adminMasterRepo.loginAndFetchAdmin(login);
	}

	 
	@Override
	public Profile getAdminProfile(int adminId) {
		return adminMasterRepo.getAdminProfile(adminId);
	}
	
	@Override
	public Boolean updateAdminProfile(int adminId, AdminMaster updatedAdmin) {
		return adminMasterRepo.updateAdminProfile(adminId, updatedAdmin);
	}

	

}
