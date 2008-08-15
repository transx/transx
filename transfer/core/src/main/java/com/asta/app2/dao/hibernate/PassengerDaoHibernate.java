/* *Class created on [ Jun 7, 2008 | 12:19:52 AM ] */ 
package com.asta.app2.dao.hibernate;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.asta.app2.dao.PassengerDao;
import com.asta.app2.model.Passenger;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class PassengerDaoHibernate extends GenericDaoHibernate<Passenger, Long> implements PassengerDao{

	public PassengerDaoHibernate() {
		super(Passenger.class);
	}

	public List<Passenger> findPassengerByLastName(String lastName) {
		return getHibernateTemplate().find("from Passenger where last_name=?",lastName);
	}

	public void removePassenger(long id) {
		try{
			getHibernateTemplate().delete(this.get(id));
		}catch(ConstraintViolationException cve){
			log.error("ConstraintViolationException accour while delete passenger by id :"+id);
		}catch(Exception e){
			log.error("Exception accour while delete passenger by id :"+id);
		}
	}

}


