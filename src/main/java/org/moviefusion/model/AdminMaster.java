package org.moviefusion.model;

import lombok.Data;

@Data
public class AdminMaster {

	private int adminId;
    private String admin_name;
    private String email;
    private String password;
    private int userRoleId;
}
 