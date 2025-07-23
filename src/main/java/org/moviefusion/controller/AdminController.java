package org.moviefusion.controller;

import org.moviefusion.model.AdminLogin;
import org.moviefusion.model.AdminMaster;
import org.moviefusion.model.Profile;
import org.moviefusion.service.AdminMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class AdminController {
	
	@Autowired
	AdminMasterService adminMasterService;
	 
	
	@PostMapping("/admin/register")
	public String isRegisterAdmin(@RequestBody AdminMaster adminMaster)
	{
		if(adminMasterService.isRegisterAdmin(adminMaster))
			return "User Registred..!!";
		else
			return "User Registration failed...!!";
	}
	
	@PostMapping("/admin/login")
	 public ResponseEntity<?> isLoginAdmin(@RequestBody AdminLogin login) {
	     try {
	         System.out.println("Received Login admin data: " + login);

	         Profile admin = adminMasterService.loginAndFetchAdmin(login);

	         if (admin != null) {
	             return ResponseEntity.ok(admin); // send full admin_master details (adminId, admin_name, email)
	         } else {
	             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed...!!");
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	     }
	 }
	
	@GetMapping("/admin/profile/{adminid}")
    public ResponseEntity<Profile> getAdminProfile(@PathVariable("adminid") Integer adminId) {
		System.out.println("Fetching Admin Profile for ID: " + adminId);

	 Profile admin = adminMasterService.getAdminProfile(adminId);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
	@PutMapping("/admin/profile/update/{adminid}") 
	public ResponseEntity<?> updateAdminProfile(@PathVariable("adminid") Integer adminId, @RequestBody AdminMaster updatedAdmin) {
		System.out.println("Updating user: " + adminId + " with name: " + updatedAdmin.getAdmin_name() + " and email: " + updatedAdmin.getEmail());
		adminMasterService.updateAdminProfile(adminId, updatedAdmin);
		return ResponseEntity.ok(updatedAdmin);

	}

}
