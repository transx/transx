/* *Class created on [ Jun 7, 2008 | 12:34:50 AM ] */ 
package com.asta.app2.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.asta.app2.Constants;
import com.asta.app2.dao.PassengerDao;
import com.asta.app2.model.Passenger;
import com.asta.app2.model.Service;
import com.asta.app2.service.PassengerManager;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class PassengerManagerImpl extends GenericManagerImpl<Passenger, Long>implements PassengerManager{
	private PassengerDao passengerDao;
	public PassengerManagerImpl(PassengerDao passengerDao) {
		super(passengerDao);
		this.passengerDao = passengerDao;
	}
	public List<Passenger> findPassengerByLastName(String lastName) {
		return passengerDao.findPassengerByLastName(lastName);
	}
	
	// COREECT ME !!!
	// this part must be change to return the passenger with specific service
	public Map<String, String> getMap(boolean withEmpty, Service service) {
		List<Passenger> passengers = passengerDao.getAll();
		Map<String, String> map = new TreeMap<String, String>();
		map.clear();
		if (withEmpty)
			map.put(Constants.EMPTY, "- - - - - - - - - -");
		for (Passenger passenger : passengers) {
			map.put(passenger.toString() == null ? "" : passenger.toString(), passenger
					.getId().toString());
		}
		return map;
	}
	public void removePassenger(long id) {
		passengerDao.removePassenger(id);
	}

}


