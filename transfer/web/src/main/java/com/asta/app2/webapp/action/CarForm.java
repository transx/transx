package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.Map;

import com.asta.app2.model.Car;
import com.asta.app2.service.CarKindManager;
import com.asta.app2.service.CarManager;
import com.asta.app2.service.PersonManager;

public class CarForm extends BasePage implements Serializable {
	private CarManager carManager;
	private Car car = new Car();
	private Long id;
	private String carKindID;
	private String personID;
	private CarKindManager carKindManager;
	private PersonManager personManager;
	private Map<String, String> carKindMap;

	public void setCarKindManager(CarKindManager carKindManager) {
		this.carKindManager = carKindManager;
	}

	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

	public String getCarKindID() {
		return carKindID;
	}

	public void setCarKindID(String carKindID) {
		this.carKindID = carKindID;
	}

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public void setCarManager(CarManager carManager) {
		this.carManager = carManager;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String delete() {
		try{
			carManager.remove(car.getId());
			addMessage("car.deleted");
		}catch(Exception e){
			addError("errors.delete.exception");
			return "edit";
		}

		return "list";
	}

	public String edit() {
		if (id != null) {
			car = carManager.get(id);
			setCarKindID(car.getCarKind().getId().toString());
			setPersonID(car.getPerson().getId().toString());
		} else {
			car = new Car();
		}

		return "edit";
	}

	public String save() {
		boolean isNew = (car.getId() == null);
		car.setCarKind(carKindManager.get(Long.valueOf(getCarKindID())
				.longValue()));
		car.setPerson(personManager
				.get(Long.valueOf(getPersonID()).longValue()));
		car.setCompany(getCurrentUser().getCompany());
		carManager.save(car);

		String key = (isNew) ? "car.added" : "car.updated";
		addMessage(key);

		if (isNew) {
			return "list";
		} else {
			return "edit";
		}
	}

	public Map<String, String> getCarKindMap() {
		if (carKindMap == null)
			carKindMap = carKindManager.getMap(false);
		return carKindMap;
	}
}