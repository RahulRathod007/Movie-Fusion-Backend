package org.moviefusion.model;

public class Profile {

	    private int user_id;
	    private String user_name;
	    private String email;

	    // Constructors
	    public Profile() {}

	    public Profile(int user_id, String user_name, String email) {
	        this.user_id = user_id;
	        this.user_name = user_name;
	        this.email = email;
	    }

	    // Getters and Setters
	    public int getUser_id() {
	        return user_id;
	    }

	    public void setUser_id(int user_id) {
	        this.user_id = user_id;
	    }

	    public String getUser_name() {
	        return user_name;
	    }

	    public void setUser_name(String user_name) {
	        this.user_name = user_name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }
	 


}
