/* *Class created on [ Jul 26, 2008 | 7:12:41 PM ] */ 
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.InsuranceBadanehDao;
import com.asta.app2.model.InsuranceBadaneh;
import com.asta.app2.model.enums.Quality;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class InsuranceBadanehDaoHibenrate extends GenericDaoHibernate<InsuranceBadaneh, Long> implements InsuranceBadanehDao{

	public InsuranceBadanehDaoHibenrate() {
		super(InsuranceBadaneh.class);
	}

	public List<InsuranceBadaneh> findIBbyQualityJodaganehSpace(
			Quality quality, Boolean jodaganeh, Long space) {
		return getHibernateTemplate().find("from InsuranceBadaneh as insur where insur.quality=? and insur.jodaganeh=? and insur.max>=? and insur.min<=?",new Object[]{quality,jodaganeh,space,space});
	}

}


