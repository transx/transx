package com.asta.app2.dao;

import java.util.List;

import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.asta.app2.model.Company;
import com.asta.app2.model.Role;
import com.asta.app2.model.User;

/**
 * User Data Access Object (GenericDao) interface.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface UserDao extends GenericDao<User, Long> {

	/**
	 * Gets users information based on login name.
	 * 
	 * @param username
	 *            the user's username
	 * @return userDetails populated userDetails object
	 * @throws
	 *         org.springframework.security.userdetails.UsernameNotFoundException
	 *         thrown when user not found in database
	 */
	@Transactional
	UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException;

	/**
	 * Gets a list of users ordered by the uppercase version of their username.
	 * 
	 * @return List populated list of users
	 */
	List<User> getUsers();

	/**
	 * Saves a user's information.
	 * 
	 * @param user
	 *            the object to be saved
	 * @return the persisted User object
	 */
	User saveUser(User user);

	/**
	 * Saves a user's information.
	 * 
	 * @param user
	 *            the object to be saved
	 * @return the persisted User object
	 */
	User saveUserSignup(User user);

	/**
	 * Retrieves the password in DB for a user
	 * 
	 * @param username
	 *            the user's username
	 * @return the password in DB, if the user is already persisted
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	String getUserPassword(String username);

	/**
	 * Gets a list of users
	 * 
	 * @param  Role of User
	 * @return List populated of users with specific role
	 */
	List<User> findUsersByThisRole(Company company,Role role);

	/**
	 * Gets a list of users ordered by the uppercase version of their username and filter by Company entity.
	 * 
	 * @return List populated list of users
	 */
	List<User> getUsersByCompany(Company company);

}
