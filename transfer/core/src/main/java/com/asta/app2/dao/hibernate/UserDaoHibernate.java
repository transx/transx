package com.asta.app2.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.asta.app2.dao.UserDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Role;
import com.asta.app2.model.User;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve User objects.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler</a> Extended to
 *         implement Acegi UserDetailsService interface by David Carter
 *         david@carter.net Modified by <a href="mailto:bwnoll@gmail.com">Bryan
 *         Noll</a> to work with the new BaseDaoHibernate implementation that
 *         uses generics.
 */
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements
		UserDao, UserDetailsService {

	/**
	 * Constructor that sets the entity to User.class.
	 */
	public UserDaoHibernate() {
		super(User.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		return getHibernateTemplate().find(
				"from User u order by upper(u.username)");
	}

	/**
	 * {@inheritDoc}
	 */
	public User saveUser(User user) {
		log.debug("user's id: " + user.getId());
		getHibernateTemplate().saveOrUpdate(user);
		// necessary to throw a DataIntegrityViolation and catch it in
		// UserManager
		getHibernateTemplate().flush();
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	public User saveUserSignup(User user) {
		log.debug("user's id: " + user.getId());
		user.setVersion(1);
		getHibernateTemplate().save(user);
		// necessary to throw a DataIntegrityViolation and catch it in
		// UserManager
		getHibernateTemplate().flush();
		return user;
	}

	/**
	 * Overridden simply to call the saveUser method. This is happenening
	 * because saveUser flushes the session and saveObject of BaseDaoHibernate
	 * does not.
	 * 
	 * @param user
	 *            the user to save
	 * @return the modified user (with a primary key set if they're new)
	 */
	@Override
	public User save(User user) {
		return this.saveUser(user);
	}

	/**
	 * {@inheritDoc}
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		List users = getHibernateTemplate().find("from User where username=?",
				username);
		if (users == null || users.isEmpty()) {
			throw new UsernameNotFoundException("user '" + username
					+ "' not found...");
		} else {
			return (UserDetails) users.get(0);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String getUserPassword(String username) {
		SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(
				SessionFactoryUtils.getDataSource(getSessionFactory()));
		Table table = AnnotationUtils.findAnnotation(User.class, Table.class);
		return jdbcTemplate.queryForObject("select password from "
				+ table.name() + " where username=?", String.class, username);

	}

	/**
	 * {@inheritDoc}
	 * this method doesn't work corrctly !!! and need to be change asap.
	 */
	public List<User> findUsersByThisRole(Company company,Role role) {
		
		return  getHibernateTemplate().find("from User");
/*		if (roles.get(0) != null){
			User entity = new User();
			entity.addRole(roles.get(0));
			if (userIds.size() > 0) {
				for (Long userId : userIds) {
					users.add(this.get(userId));
				}
			}
		}
		return users;
 */
	}

	/**
	 * {@inheritDoc}
	 */
	public List<User> getUsersByCompany(Company company) {
		return getHibernateTemplate().find("from User u where u.company=? order by upper(u.username)",company);
	}
}
