/* *Class created on [ Jun 4, 2008 | 9:51:53 PM ] */ 
package com.asta.app2.service;

import java.util.List;
import java.util.Map;

import com.asta.app2.model.Company;
import com.asta.app2.model.Lodger;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface LodgerManager extends GenericManager<Lodger, Long>{

	List<Lodger> findLodgerByPresenter(Company company,String presenter);

	Map<String, String> getMap(Company company,boolean withEmpty);
}


