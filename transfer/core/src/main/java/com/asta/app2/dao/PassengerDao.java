/* *Class created on [ Jun 7, 2008 | 12:18:14 AM ] */ 
package com.asta.app2.dao;

import java.util.List;

import com.asta.app2.model.Passenger;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface PassengerDao extends GenericDao<Passenger, Long>{
	List<Passenger> findPassengerByLastName(String lastName);

	void removePassenger(long id);

}


