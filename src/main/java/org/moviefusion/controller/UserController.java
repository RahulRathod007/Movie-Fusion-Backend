package org.moviefusion.controller;

import org.moviefusion.model.Profile;
import org.moviefusion.model.UserLogin;
import org.moviefusion.model.UserMaster;
import org.moviefusion.service.UserMasterService;
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

import jakarta.servlet.http.HttpSession;


@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	
	@Autowired
	UserMasterService userMasterService;  
	
	@PostMapping("/user/register")
	public String isRegisterUser(@RequestBody UserMaster user_master) {
	    try {
	        System.out.println("Received user data: " + user_master);
	        boolean isRegistered = userMasterService.isRegisterUser(user_master);
	        return isRegistered ? "User Registered.....!!" : "User Not Registered.....!!";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    }
	}

	 @GetMapping("/user/profile/{userid}")
    public ResponseEntity<Profile> getUserProfile(@PathVariable("userid") Integer userId) {
	 Profile user = userMasterService.getUserProfile(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	 
	 @PostMapping("/user/login")
	 public ResponseEntity<?> isLoginUser(@RequestBody UserLogin login) {
	     try {
	         System.out.println("Received Login user data: " + login);

	         Profile user = userMasterService.loginAndFetchUser(login);

	         if (user != null) {
	             return ResponseEntity.ok(user); // send full user details (userId, name, email)
	         } else {
	             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed...!!");
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	     }
	 }


	@PutMapping("/user/profile/update/{userid}")
	public ResponseEntity<?> updateUserProfile(@PathVariable("userid") int userId, @RequestBody UserMaster updatedUser) {
		System.out.println("Updating user: " + userId + " with name: " + updatedUser.getUser_name() + " and email: " + updatedUser.getEmail());
		userMasterService.updateUserProfile(userId, updatedUser);
		return ResponseEntity.ok(updatedUser);

	}
	
	 @GetMapping("user/logout")
	    public String logout(HttpSession session) {
	        session.invalidate();
	        return "Logged out successfully.";
	    }


}
