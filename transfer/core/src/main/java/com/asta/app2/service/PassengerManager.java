/* *Class created on [ Jun 7, 2008 | 12:33:42 AM ] */ 
package com.asta.app2.service;

import java.util.List;
import java.util.Map;

import com.asta.app2.model.Passenger;
import com.asta.app2.model.Service;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public interface PassengerManager extends GenericManager<Passenger, Long>{
	List<Passenger> findPassengerByLastName(String lastName);

	Map<String, String> getMap(boolean withEmpty, Service service);
	
	void removePassenger(long id);
}


