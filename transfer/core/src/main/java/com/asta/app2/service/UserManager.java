package com.asta.app2.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.asta.app2.dao.UserDao;
import com.asta.app2.exception.UserExistsException;
import com.asta.app2.model.Company;
import com.asta.app2.model.Role;
import com.asta.app2.model.User;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a> 
 */
public interface UserManager extends UniversalManager {

    /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setUserDao(UserDao userDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    User getUser(String userId);

    /**
     * Finds a user by their username.
     * @param username the user's username used to login
     * @return User a populated user object
     * @throws org.springframework.security.userdetails.UsernameNotFoundException
     *         exception thrown when user not found
     */
    User getUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Retrieves a list of users, filtering with parameters on a user object
     * @param user parameters to filter on
     * @return List
     */
    List getUsers(User user);

    /**
     * Retrieves a list of users, filtering with parameters on a user object
     * @param user parameters to filter on
     * @return List
     */
    List getUsersByCompany(Company company);

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    User saveUser(User user) throws UserExistsException;

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    User saveUserSignup(User user) throws UserExistsException;

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeUser(String userId);

    /**
     * Retrieves a list of users , filtering with specific role (e.g. ROLE_CASHIER)
     *
     * @param withEmpty the boolean value for add empty row at the first value of map
     * @param role_name the String for filter result
     * @return Map of string string for fill the selectOneMenu
     */
	Map<String, String> getSpecificMap(boolean withEmpty,String role_name);

	/**
	 * Retrieves a list of users , filtering with specific role (ROLE_CASHIER)
	 *
	 * @param company the company entity for filter result
	 * @param withEmpty the boolean value for add empty row at the first value of map
	 * @param role_name the String for filter result
	 * @return Map of string string for fill the selectOneMenu
	 */
	Map<String, String> getSpecificMap(Company company,boolean withEmpty,String role_name);
}
