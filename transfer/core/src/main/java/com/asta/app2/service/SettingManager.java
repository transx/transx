/* *Class created on [ Jul 22, 2008 | 11:02:54 PM ] */ 
package com.asta.app2.service;

import com.asta.app2.model.Company;
import com.asta.app2.model.Setting;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface SettingManager extends GenericManager<Setting, Long>{

	Setting getSettingByCompany(Company company);

}


