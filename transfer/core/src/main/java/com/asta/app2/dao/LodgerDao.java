/* *Class created on [ Jun 4, 2008 | 9:34:29 PM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Company;
import com.asta.app2.model.Lodger;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface LodgerDao extends GenericDao<Lodger, Long>{

	List<Lodger> findLodgerByPresenter(Company company,String presenter);

	List<Lodger> getAllLodgerByCompany(Company company);
}


