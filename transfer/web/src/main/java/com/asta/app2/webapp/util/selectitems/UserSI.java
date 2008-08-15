package com.asta.app2.webapp.util.selectitems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.convert.Converter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.asta.app2.model.LabelValueGeneric;
import com.asta.app2.model.User;
import com.asta.app2.service.UserManager;
import com.asta.app2.webapp.jsf.ReferenceConverter;

/**
 * @author Rene Guenther rene.guenther@innflow.com
 */
@Controller("userSI")
@Scope("request")
public class UserSI extends BaseSI implements BaseSIIntf<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5816369678066099896L;

	private UserManager manager;

	private Map<String, User> selectItems;

	private List<User> allObjects;

//	@Resource(name = "userManager")
	public void setManager(UserManager manager) {
		this.manager = manager;
	}

	public Map<String, User> getSelectItems() {
		return getSelectItems(getRequest().getLocale());
	}

	public Map<String, User> getSelectItems(Locale locale) {
		if (this.selectItems == null) {
			allObjects = manager.getUsers(null);
			List<LabelValueGeneric<User>> objects = new ArrayList<LabelValueGeneric<User>>();
			for (User user : allObjects) {
				final String label = user.getUsername() + " ("
						+ user.getFullName() + ")";
				objects.add(new LabelValueGeneric<User>(label, user));
			}
			if (null == locale)
				Collections.sort(objects);
			else
				Collections.sort(objects, new LabelValueComparator(locale));

			Map<String, User> options = new LinkedHashMap<String, User>();
			for (LabelValueGeneric<User> lv : objects) {
				options.put(lv.getLabel(), lv.getValue());
			}
			this.selectItems = options;
		}
		return this.selectItems;
	}

	public List<User> getAllObjects() {
		if (allObjects == null) {
			allObjects = manager.getUsers(null);
		}
		return allObjects;
	}

	public Converter getConverter() {
		return new ReferenceConverter<User>(getAllObjects());
	}
}