package com.asta.app2.webapp.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mail.MailException;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationTrustResolver;
import org.springframework.security.AuthenticationTrustResolverImpl;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.asta.app2.Constants;
import com.asta.app2.model.Role;
import com.asta.app2.model.User;
import com.asta.app2.service.RoleManager;
import com.asta.app2.exception.UserExistsException;
import com.asta.app2.util.BundleUtil;
import com.asta.app2.util.ConvertUtil;
import com.asta.app2.webapp.util.RequestUtil;

/**
 * JSF Page class to handle editing a user with a form.
 *
 * @author mraible 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class UserForm extends BasePage implements Serializable {
    private static final long serialVersionUID = -1141119853856863204L;
    private RoleManager roleManager;
    private String id;
    private User user = new User();
    private Map<String, String> availableRoles;
    private Map<String, String> availableRolesForAdmin;
    private String[] userRoles;

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    public String add() {
        user = new User();
        user.setEnabled(true);
        user.addRole(new Role(Constants.USER_ROLE));
        return "editProfile";
    }

    public String cancel() {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'cancel' method");
        }

        if (!"list".equals(getParameter("from"))) {
            return "mainMenu";
        } else {
            return "cancel";
        }
    }

    public String edit() {
        HttpServletRequest request = getRequest();
        
        // if a user's id is passed in
        if (id != null) {
            // lookup the user using that id
            user = userManager.getUser(id);
        } else {
            user = userManager.getUserByUsername(request.getRemoteUser());
        } 

        if (user.getUsername() != null) {
        	// comment this line case prompt for get ConfirmPassword each time in edit profile. 
        	user.setConfirmPassword(user.getPassword());

            if (isRememberMe()) {
                // if user logged in with remember me, display a warning that they can't change passwords
                log.debug("checking for remember me login...");
                log.trace("User '" + user.getUsername() + "' logged in with cookie");
                addMessage("userProfile.cookieLogin");
            }
        }

        return "editProfile";
    }

    public String editElseUser() {
    	HttpServletRequest request = getRequest();
    	
    	// if a user's id is passed in
    	if (id != null) {
    		// lookup the user using that id
    		user = userManager.getUser(id);
    	} else {
    		user = userManager.getUserByUsername(request.getRemoteUser());
    	} 
    	
    	if (user.getUsername() != null) {
    		//user.setConfirmPassword(user.getPassword());
//    		if (isRememberMe()) {
//    			// if user logged in with remember me, display a warning that they can't change passwords
//    			log.debug("checking for remember me login...");
//    			log.trace("User '" + user.getUsername() + "' logged in with cookie");
//    			addMessage("userProfile.cookieLogin");
//    		}
    	}
    	
    	return "editElseUser";
    }

    /**
     * Convenience method for view templates to check if the user is logged in with RememberMe (cookies).
     * @return true/false - false if user interactively logged in.
     */
    public boolean isRememberMe() {
        if (user != null && user.getId() == null) return false; // check for add()
        
        AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
        SecurityContext ctx = SecurityContextHolder.getContext();

        if (ctx != null) {
            Authentication auth = ctx.getAuthentication();
            return resolver.isRememberMe(auth);
        }
        return false;
    }

    /**
     * for make editElseUser more easy this method will hide password temporary !
     * @return true/false.
     */
    public boolean isRememberMeOwner() {
    	return true;
    }

    public String save() throws IOException {

        // workaround for plain ol' HTML input tags that don't seem to set
        // properties on the managed bean
        setUserRoles(getRequest().getParameterValues("userForm:userRoles"));
        
        for (int i = 0; (userRoles != null) && (i < userRoles.length); i++) {
            String roleName = userRoles[i];
            user.addRole(roleManager.getRole(roleName));
        }
        
        //Users have relation with each other with their Company , so
        user.setCompany(getCurrentUser().getCompany());
        
        Integer originalVersion = user.getVersion();

        // For some reason, Canoo WebTest causes version to be 0. Set it to null so test will pass.
        if (user.getVersion() != null && user.getVersion() == 0) {
            user.setId(null);
            user.setVersion(null);
        }

        try {
            user = userManager.saveUser(user);
        } catch (AccessDeniedException ade) {
            // thrown by UserSecurityAdvice configured in aop:advisor userManagerSecurity
            log.warn(ade.getMessage());
            getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        } catch (UserExistsException e) {
            addError("errors.existing.user", new Object[] { user.getUsername(), user.getEmail() });

            // reset the version # to what was passed in
            user.setVersion(originalVersion);
            return "editProfile";
        }

        if (!"list".equals(getParameter("from"))) {
            // add success messages
            addMessage("user.saved");

            // return to main Menu
            return "mainMenu";
        } else {
            // add success messages
            if ("".equals(getParameter("userForm:version"))) {
                addMessage("user.added", user.getFullName());

                try {
                    sendUserMessage(user, getText("newuser.email.message",
                                    user.getFullName()), RequestUtil.getAppURL(getRequest()));
                } catch (MailException me) {
                    addError(me.getCause().getLocalizedMessage());
                }

                return "list"; // return to list screen
            } else {
                addMessage("user.updated.byAdmin", user.getFullName());
                return "editProfile"; // return to current page
            }
        }
    }

    public String saveElseUser() throws IOException {
    	
    	user.getRoles().clear();
    	// workaround for plain ol' HTML input tags that don't seem to set
    	// properties on the managed bean
    	setUserRoles(getRequest().getParameterValues("userForm:userRoles"));
    	
    	for (int i = 0; (userRoles != null) && (i < userRoles.length); i++) {
    		String roleName = userRoles[i];
    		user.addRole(roleManager.getRole(roleName));
    	}
    	
    	//Users have relation with each other with their Company , so
        //user.setCompany(getCurrentUser().getCompany());
    	
    	Integer originalVersion = user.getVersion();
    	
    	// For some reason, Canoo WebTest causes version to be 0. Set it to null so test will pass.
    	if (user.getVersion() != null && user.getVersion() == 0) {
    		user.setId(null);
    		user.setVersion(null);
    	}
    	
    	try {
    		user = userManager.saveUser(user);
    	} catch (AccessDeniedException ade) {
    		// thrown by UserSecurityAdvice configured in aop:advisor userManagerSecurity
    		log.warn(ade.getMessage());
    		getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
    		return null;
    	} catch (UserExistsException e) {
    		addError("errors.existing.user", new Object[] { user.getUsername(), user.getEmail() });
    		
    		// reset the version # to what was passed in
    		user.setVersion(originalVersion);
    		return "editElseUser";
    	}
    	
    	if (!"list".equals(getParameter("from"))) {
    		// add success messages
    		addMessage("user.saved");
    		
    		// return to main Menu
    		return "mainMenu";
    	} else {
    		// add success messages
    		if ("".equals(getParameter("userForm:version"))) {
    			addMessage("user.added", user.getFullName());
    			
    			try {
    				sendUserMessage(user, getText("newuser.email.message",
    						user.getFullName()), RequestUtil.getAppURL(getRequest()));
    			} catch (MailException me) {
    				addError(me.getCause().getLocalizedMessage());
    			}
    			
    			return "list"; // return to list screen
    		} else {
    			addMessage("user.updated.byAdmin", user.getFullName());
    			return "editElseUser"; // return to current page
    		}
    	}
    }
    

    public String delete() {
        userManager.removeUser(getUser().getId().toString());
        addMessage("user.deleted", getUser().getFullName());

        return "list";
    }

    /**
     * Convenience method to determine if the user came from the list screen
     * @return String
     */
    public String getFrom() {
        if ((id != null) || (getParameter("editUser:add") != null) ||
                ("list".equals(getParameter("from")))) {
            return "list";
        }

        return "";
    }

    // Form Controls ==========================================================
    @SuppressWarnings("unchecked")
    public Map<String, String> getAvailableRoles() {
        if (availableRoles == null) {
            List roles = (List) getServletContext().getAttribute(Constants.AVAILABLE_ROLES);
            availableRoles = ConvertUtil.convertListToMap(roles);
        }
        availableRoles.remove(BundleUtil.getMessageBundle(Constants.ADMIN_ROLE));
        return availableRoles;
    }

    public Map<String, String> getAvailableRolesForAdmin() {
    	if (availableRolesForAdmin == null) {
    		List roles = (List) getServletContext().getAttribute(Constants.AVAILABLE_ROLES);
    		availableRolesForAdmin = ConvertUtil.convertListToMap(roles);
    	}
    	return availableRolesForAdmin;
    }

    public String[] getUserRoles() {
        userRoles = new String[user.getRoles().size()];

        int i = 0;

        if (userRoles.length > 0) {
            for (Role role : user.getRoles()) {
                userRoles[i] = role.getName();
                i++;
            }
        }

        return userRoles;
    }

    public void setUserRoles(String[] userRoles) {
        this.userRoles = userRoles;
    }
    
    public String getCountry() {
        return getUser().getAddress().getCountry();
    }
    
    // for some reason, the country drop-down won't do 
    // getUser().getAddress().setCountry(value)
    public void setCountry(String country) {
        getUser().getAddress().setCountry(country);
    }
}
