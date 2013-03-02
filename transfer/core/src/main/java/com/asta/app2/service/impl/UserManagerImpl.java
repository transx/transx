package com.asta.app2.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.asta.app2.Constants;
import com.asta.app2.dao.UserDao;
import com.asta.app2.exception.UserExistsException;
import com.asta.app2.model.Company;
import com.asta.app2.model.Role;
import com.asta.app2.model.User;
import com.asta.app2.service.UserManager;
import com.asta.app2.service.UserService;


/**
 * Implementation of UserManager interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@WebService(serviceName = "UserService", endpointInterface = "com.asta.app2.service.UserService")
public class UserManagerImpl extends UniversalManagerImpl implements UserManager, UserService {
    private UserDao dao;
    private PasswordEncoder passwordEncoder;
    
    /**
     * Set the Dao for communication with the data layer.
     * @param dao the UserDao that communicates with the database
     */
    @Required
    public void setUserDao(UserDao dao) {
        this.dao = dao;
    }

    /**
     * Set the PasswordEncoder used to encrypt passwords.
     * @param passwordEncoder the PasswordEncoder implementation
     */
    @Required
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * {@inheritDoc}
     */
    public User getUser(String userId) {
        return dao.get(new Long(userId));
    }

    /**
     * {@inheritDoc}
     */
    public List<User> getUsers(User user) {
        return dao.getUsers();
    }
    
    
    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) throws UserExistsException {

        if (user.getVersion() == null) {
            // if new user, lowercase userId
            user.setUsername(user.getUsername().toLowerCase());
        }
        
        // Get and prepare password management-related artifacts
        boolean passwordChanged = false;
        if (passwordEncoder != null) {
            // Check whether we have to encrypt (or re-encrypt) the password
            if (user.getVersion() == null) {
                // New user, always encrypt
                passwordChanged = true;
            } else {
                // Existing user, check password in DB
                String currentPassword = dao.getUserPassword(user.getUsername());
                if (currentPassword == null) {
                    passwordChanged = true;
                } else {
                    if (!currentPassword.equals(user.getPassword())) {
                        passwordChanged = true;
                    }
                }
            }

            // If password was changed (or new user), encrypt it
            if (passwordChanged) {
                user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
            }
        } else {
            log.warn("PasswordEncoder not set, skipping password encryption...");
        }
        
        try {
            return dao.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        } catch (EntityExistsException e) { // needed for JPA
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public User saveUserSignup(User user) throws UserExistsException {
    	
    	if (user.getVersion() == null) {
    		// if new user, lowercase userId
    		user.setUsername(user.getUsername().toLowerCase());
    	}
    	
    	// Get and prepare password management-related artifacts
    	boolean passwordChanged = false;
    	if (passwordEncoder != null) {
    		// Check whether we have to encrypt (or re-encrypt) the password
    		if (user.getVersion() == null) {
    			// New user, always encrypt
    			passwordChanged = true;
    		} else {
    			// Existing user, check password in DB
    			String currentPassword = dao.getUserPassword(user.getUsername());
    			if (currentPassword == null) {
    				passwordChanged = true;
    			} else {
    				if (!currentPassword.equals(user.getPassword())) {
    					passwordChanged = true;
    				}
    			}
    		}
    		
    		// If password was changed (or new user), encrypt it
    		if (passwordChanged) {
    			user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
    		}
    	} else {
    		log.warn("PasswordEncoder not set, skipping password encryption...");
    	}
    	
    	//this part added for fix the signuped user can have ability to edit his/her profile .
    	//(doesn't get the username existed exception!)
//        if (user.getVersion() == null && user.getVersion() == 0){
//        	user.setVersion(1);
//        }
    	
    	try {
    		return dao.saveUserSignup(user);
    	} catch (DataIntegrityViolationException e) {
    		e.printStackTrace();
    		log.warn(e.getMessage());
    		throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
    	}
//        catch (EntityExistsException e) { // needed for JPA
//            e.printStackTrace();
//            log.warn(e.getMessage());
//            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
//        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeUser(String userId) {
        log.debug("removing user: " + userId);
        dao.remove(new Long(userId));
    }

    /**
     * {@inheritDoc}
     * @param username the login name of the human
     * @return User the populated user object
     * @throws UsernameNotFoundException thrown when username not found
     */
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return (User) dao.loadUserByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
	public Map<String, String> getSpecificMap(boolean withEmpty, String role_name) {
		List<User> users = dao.getAllDistinct();
		Map<String, String> map = new TreeMap<String, String>();
		map.clear();
		if (withEmpty)
			map.put("- - - - - - - - - -", Constants.EMPTY);

		for (User user : users) {
			if (user.getRoles().size() > 0 ){
				for (Role elm : user.getRoles()){
					if (elm.getAuthority().equals(role_name)){
						map.put(user.getUsername(), user.getId().toString());
					}
				}
			}
		}
		return map;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, String> getSpecificMap(Company company,boolean withEmpty, String role_name) {
		User userExample = new User();
		userExample.setCompany(new Company(company.getId()));
		List<User> users = dao.searchByExample(userExample);
		Map<String, String> map = new TreeMap<String, String>();
		map.clear();
		if (withEmpty)
			map.put("- - - - - - - - - -", Constants.EMPTY);
		
		for (User user : users) {
			if (user.getRoles().size() > 0 ){
				for (Role elm : user.getRoles()){
					if (elm.getAuthority().equals(role_name)){
						map.put(user.getUsername(), user.getId().toString());
					}
				}
			}
		}
		return map;
	}

	/**
	 * {@inheritDoc}
	 */
	public List getUsersByCompany(Company company) {
		return dao.getUsersByCompany(company);
	}
 }
