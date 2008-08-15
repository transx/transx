package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.asta.app2.Constants;
import com.asta.app2.model.Role;
import com.asta.app2.model.User;

public class UserList extends BasePage implements Serializable {
    private static final long serialVersionUID = 972359310602744018L;
    
    public UserList() {
        setSortColumn("username");
    }
    
    public List getUsers() {
        return sort(userManager.getUsers(null));
    }
    
    public List<User> getUsersWithoutAdmin(){
    	List<User> users = userManager.getUsersByCompany(getCurrentUser().getCompany());
    	List<User> usersWithoutAdmin = new ArrayList<User>();
    	boolean ok = true;
    	for (User user : users) {
			for (Role role : user.getRoles()){
				if (role.getName().equals(Constants.ADMIN_ROLE)){
					ok = false;
					break;
				}
			}
			if(ok)
				usersWithoutAdmin.add(user);
			ok = true;
		}
    	
    	return sort(usersWithoutAdmin);
    }
}
