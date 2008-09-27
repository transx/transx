package com.asta.app2.webapp.action;

import java.io.Serializable;

import com.asta.app2.model.Passenger;
import com.asta.app2.model.enums.Gender;
import com.asta.app2.service.PassengerManager;

public class PassengerForm extends BasePage implements Serializable {
	private PassengerManager passengerManager;
	private Passenger passenger = new Passenger();
	private Long id;

	public void setPassengerManager(PassengerManager passengerManager) {
		this.passengerManager = passengerManager;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String delete() {
		try {
			passengerManager.remove(passenger.getId());
			addMessage("passenger.deleted");
		} catch (Exception e) {
			addError("passenger.delete.fail");
			return "edit";
		}
		return "list";
	}

	public String delete2() {
		try {
			passengerManager.removePassenger(passenger.getId());
			addMessage("passenger.deleted");
		} catch (Exception e) {
			addError("passenger.delete.fail");
			return "edit";
		}
		return "list";
	}

	public String edit() {
		if (id != null) {
			passenger = passengerManager.get(id);
		} else {
			passenger = new Passenger();
		}

		return "edit";
	}

	public String save() {
		boolean isNew = (passenger.getId() == null);
		passengerManager.save(passenger);

		String key = (isNew) ? "passenger.added" : "passenger.updated";
		addMessage(key);

		if (isNew) {
			return "list";
		} else {
			return "edit";
		}
	}

	public String changeGender() {
		if (id != null) {
			Passenger passenger = passengerManager.get(id);

			if (passenger.getGender().equals(Gender.MALE)) {
				passenger.setGender(Gender.FEMALE);
			} else {
				passenger.setGender(Gender.MALE);
			}
			passengerManager.save(passenger);
		}
		return "noWhere";
	}

}