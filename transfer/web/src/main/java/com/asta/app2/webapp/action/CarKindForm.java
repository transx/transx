package com.asta.app2.webapp.action;

import java.io.Serializable;

import javax.faces.model.SelectItem;

import com.asta.app2.model.CarKind;
import com.asta.app2.model.enums.Quality;
import com.asta.app2.model.enums.CarType;
import com.asta.app2.service.CarKindManager;

public class CarKindForm extends BasePage implements Serializable {
	private CarKindManager carKindManager;
	private CarKind carKind = new CarKind();
	private Long id;

	public void setCarKindManager(CarKindManager carKindManager) {
		this.carKindManager = carKindManager;
	}

	public CarKind getCarKind() {
		return carKind;
	}

	public void setCarKind(CarKind carKind) {
		this.carKind = carKind;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String delete() {
		carKindManager.remove(carKind.getId());
		addMessage("carKind.deleted");

		return "list";
	}

	public String edit() {
		if (id != null) {
			carKind = carKindManager.get(id);
		} else {
			carKind = new CarKind();
		}

		return "edit";
	}

	public String save() {
		boolean isNew = (carKind.getId() == null);
		carKindManager.save(carKind);
		String key = (isNew) ? "carKind.added" : "carKind.updated";
		addMessage(key);

		if (isNew) {
			return "list";
		} else {
			return "edit";
		}
	}

	public SelectItem[] getTypeItems() {
		return typeItems;
	}

	private static SelectItem[] typeItems = {
			new SelectItem(CarType.TYPE1, CarType.TYPE1.getLabel()),
			new SelectItem(CarType.TYPE2, CarType.TYPE2.getLabel()) };

	public SelectItem[] getQualityItems() {
		return qualityItems;
	}

	private static SelectItem[] qualityItems = {
			new SelectItem(Quality.UP, Quality.UP.getLabel()),
			new SelectItem(Quality.NORMAL, Quality.NORMAL.getLabel()) };
}