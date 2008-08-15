/* *Class created on [ Jun 14, 2008 | 5:22:09 PM ] */ 
package com.asta.app2.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.asta.app2.dao.ChairDao;
import com.asta.app2.model.Chair;
import com.asta.app2.model.Ticket;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */

public class ChairDaoHibernate extends GenericDaoHibernate<Chair, Long>implements ChairDao{
	public ChairDaoHibernate() {
		super(Chair.class);
	}

	// this method is NOT correct and doesn't used on this application
	public List<Chair> findChairsByServiceId(long service_id) {
		
		List<Ticket> tickets = getHibernateTemplate().find("from Ticket");
		
		List<Chair> chairs = new ArrayList<Chair>();
		if (tickets.size() >0 )
			chairs.add(get(1L));
			for (Ticket ticket : tickets) {
			}
		
		return chairs;
	}

	public List<Chair> getAllSorted(int lastId) {
		return getHibernateTemplate().find("from Chair ch where ch.id <=? order by(ch.id)",Long.valueOf(lastId).longValue());
	}

}


