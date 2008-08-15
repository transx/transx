package com.asta.app2;

/**
 * Constant values used throughout the application.
 * 
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class Constants {
	// ~ Static fields/initializers
	// =============================================

	/**
	 * The name of the ResourceBundle used in this application
	 */
	public static final String BUNDLE_KEY = "ApplicationResources";

	/**
	 * File separator from System properties
	 */
	public static final String FILE_SEP = System.getProperty("file.separator");

	/**
	 * User home from System properties
	 */
	public static final String USER_HOME = System.getProperty("user.home")
			+ FILE_SEP;

	/**
	 * The name of the configuration hashmap stored in application scope.
	 */
	public static final String CONFIG = "appConfig";

	/**
	 * Session scope attribute that holds the locale set by the user. By setting
	 * this key to the same one that Struts uses, we get synchronization in
	 * Struts w/o having to do extra work or have two session-level variables.
	 */
	public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

	/**
	 * The request scope attribute under which an editable user form is stored
	 */
	public static final String USER_KEY = "userForm";

	/**
	 * The request scope attribute that holds the user list
	 */
	public static final String USER_LIST = "userList";

	/**
	 * The request scope attribute for indicating a newly-registered user
	 */
	public static final String REGISTERED = "registered";

	/**
	 * The name of the Administrator role, as specified in web.xml
	 */
	public static final String ADMIN_ROLE = "ROLE_ADMIN";

	/**
	 * The name of the User role, as specified in web.xml
	 */
	public static final String USER_ROLE = "ROLE_USER";

	/**
	 * The name of the user's role list, a request-scoped attribute when
	 * adding/editing a user.
	 */
	public static final String USER_ROLES = "userRoles";

	/**
	 * The name of the available roles list, a request-scoped attribute when
	 * adding/editing a user.
	 */
	public static final String AVAILABLE_ROLES = "availableRoles";

	/**
	 * The name of the CSS Theme setting.
	 */
	public static final String CSS_THEME = "csstheme";

	/**
	 * The name of the Other roles
	 */
	public static final String OWNER_ROLE = "ROLE_OWNER";
	public static final String CASHIER_ROLE = "ROLE_CASHIER";
	public static final String RESERVER_ROLE = "ROLE_RESERVER";

	public static final String EMPTY = "empty";
	
	public static final String CHAIR_REGISTERED = "chairReg";
	
	public static final String CHAIR_RESERVED = "chairRev";

	public static final String AVAILABLE_RATE_TYPES = "rateTypes";

	public static final String DATE_SYSTEM = "calendarType";
	public static final String JALALI_DATE = "Jalali";
	public static final String GERIGORIAN_DATE = "Gregorian";
	
	/**
	 * The name for the navigation rule (use when we want stay in current page!)
	 */
	public static final String NO_WHERE = "noWhere";
}
