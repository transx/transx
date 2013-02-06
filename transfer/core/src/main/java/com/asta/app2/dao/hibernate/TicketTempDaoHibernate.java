/* *Class created on [ Jun 24, 2008 | 12:34:21 PM ] */ 
package com.asta.app2.dao.hibernate;

import java.util.List;

import com.asta.app2.dao.TicketTempDao;
import com.asta.app2.model.Company;
import com.asta.app2.model.Service;
import com.asta.app2.model.TicketTemp;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class TicketTempDaoHibernate extends GenericDaoHibernate<TicketTemp, Long> implements TicketTempDao{

	public TicketTempDaoHibernate() {
		super(TicketTemp.class);
	}

	public List<TicketTemp> getAllTicketTemp(Company company) {
		return getHibernateTemplate().find("from TicketTemp where company=?",company);
	}

	public List<TicketTemp> findTicketTempsByService(Company company,Service service) {
		return getHibernateTemplate().find("from TicketTemp as tt where tt.company=? and tt.service=? and tt.committed=?",new Object[]{company, service, false});
	}

	public List<TicketTemp> findTicketTempsByServiceAndPassenger(Company company,TicketTemp ticketTemp) {
		return getHibernateTemplate().find("from TicketTemp as tt where tt.company=? and tt.service=? and tt.passenger=?", new Object[]{company,ticketTemp.getService(),ticketTemp.getPassenger()});
	}
	
	public List<TicketTemp> getAllTicketTempReadyForSell(Company company) {
		return getHibernateTemplate().find("from TicketTemp as tt where tt.company=? and tt.committed=? and tt.count>0", new Object[]{ company, false });
	}

	public TicketTemp saveTicketTemp(Company company, TicketTemp ticketTemp) {
		ticketTemp.setCompany(company);
		getHibernateTemplate().saveOrUpdate(ticketTemp);
		getHibernateTemplate().flush();
		return ticketTemp;
	}


}


