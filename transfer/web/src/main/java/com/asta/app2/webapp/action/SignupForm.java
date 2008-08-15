package com.asta.app2.webapp.action;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.mail.MailException;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;

import com.asta.app2.Constants;
import com.asta.app2.model.User;
import com.asta.app2.service.CompanyManager;
import com.asta.app2.service.RoleManager;
import com.asta.app2.service.UserExistsException;
import com.asta.app2.webapp.util.RequestUtil;

/**
 * JSF Page class to handle signing up a new user.
 * 
 * @author mraible
 */
public class SignupForm extends BasePage implements Serializable {
	private static final long serialVersionUID = 3524937486662786265L;
	private User user = new User();
	private RoleManager roleManager;
	private String companyID;
	private CompanyManager companyManager;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public String save() throws Exception {
		user.setEnabled(true);

		// Set the default user role on this new user
		user.addRole(roleManager.getRole(Constants.USER_ROLE));
		
		if (getCompanyID() == null)
			setCompanyID((String)getRequest().getParameter("signupForm:company"));
		user.setCompany(companyManager.get(Long.valueOf(getCompanyID()).longValue()));
		
		try {
			user = userManager.saveUserSignup(user);
		} catch (AccessDeniedException ade) {
			// thrown by UserSecurityAdvice configured in aop:advisor
			// userManagerSecurity
			log.warn(ade.getMessage());
			getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		} catch (UserExistsException e) {
			addMessage("errors.existing.user", new Object[] {user.getUsername(), user.getEmail() });

			// redisplay the unencrypted passwords
			user.setPassword(user.getConfirmPassword());
			return null;
		}

		addMessage("user.registered");
		getSession().setAttribute(Constants.REGISTERED, Boolean.TRUE);

		// log user in automatically
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getConfirmPassword(), user.getAuthorities());
		auth.setDetails(user);
		SecurityContextHolder.getContext().setAuthentication(auth);

		// Send an account information e-mail
		message.setSubject(getText("signup.email.subject"));

		try {
			sendUserMessage(user, getText("signup.email.message"), RequestUtil.getAppURL(getRequest()));
		} catch (MailException me) {
			addError(me.getMostSpecificCause().getMessage());
			return null;
		}

		return "mainMenu";
	}

	public String getCountry() {
		return getUser().getAddress().getCountry();
	}

	// for some reason, the country drop-down won't do
	// getUser().getAddress().setCountry(value)
	public void setCountry(String country) {
		getUser().getAddress().setCountry(country);
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}
}