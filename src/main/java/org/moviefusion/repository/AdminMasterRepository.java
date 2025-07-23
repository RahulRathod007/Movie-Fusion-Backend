package org.moviefusion.repository;

import org.moviefusion.model.AdminLogin;
import org.moviefusion.model.AdminMaster;
import org.moviefusion.model.Profile;

public interface AdminMasterRepository {
	
	public boolean isRegisterAdmin(AdminMaster adminmaster); 
	
	public Profile loginAndFetchAdmin(AdminLogin login);
	
	public Profile getAdminProfile(int adminId);
	
	public Boolean updateAdminProfile(int adminId, AdminMaster updatedAdmin);
	
	

}
