/* *Class created on [ Jun 5, 2008 | 5:28:58 PM ] */
package com.asta.app2.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.asta.app2.Constants;
import com.asta.app2.dao.CarDao;
import com.asta.app2.model.Car;
import com.asta.app2.model.CarKind;
import com.asta.app2.model.Company;
import com.asta.app2.service.CarManager;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CarManagerImpl extends GenericManagerImpl<Car, Long> implements
		CarManager {
	private CarDao carDao;

	public CarManagerImpl(CarDao carDao) {
		super(carDao);
		this.carDao = carDao;
	}


	public List<Car> getAllCarByCompany(Company company) {
		return carDao.getAllCarByCompany(company);
	}
	
	public List<Car> findCarByCode(Company company,String code) {
		return carDao.findCarByCode(company,code);
	}
	
	public Map<String, String> getMap(Company company,boolean withEmpty) {
		List<Car> cars = carDao.getAllCarByCompany(company);

		Map<String, String> map = new LinkedHashMap<String, String>();

		if (withEmpty)
			map.put("----------", Constants.EMPTY);

		for (Car car : cars) {
			map.put(car.toString() == null ? "" : car.toString(), car.getId()
					.toString());
		}
		return map;
	}

	public Map<String, String> getCarKindMap(Company company,CarKind carKind) {
		List<Car> cars = carDao.getAllCarByKind(company,carKind);

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.clear();
		map.put("----------", Constants.EMPTY);
		if (cars.size()>0){
			for (Car car : cars) {
				map.put(car.toString() == null ? "" : car.toString(), car.getId()
						.toString());
			}
		}
		return map;
	}

}
