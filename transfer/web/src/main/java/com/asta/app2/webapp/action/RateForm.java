package com.asta.app2.webapp.action;

import java.io.Serializable;
import java.util.List;

import com.asta.app2.model.Rate;
import com.asta.app2.webapp.action.BasePage;
import com.asta.app2.service.CarKindManager;
import com.asta.app2.service.PathManager;
import com.asta.app2.service.RateManager;

public class RateForm extends BasePage implements Serializable {
	private RateManager rateManager;
	private Rate rate = new Rate();
	private Long id;
	private String carKindID;
	private String pathID;
	private PathManager pathManager;
	private CarKindManager carKindManager;

	public String delete() {
		rateManager.remove(rate.getId());
		addMessage("rate.deleted");

		return "list";
	}

	public String edit() {
		if (id != null) {
			rate = rateManager.get(id);
			setPathID(rate.getPath().getId().toString());
		} else {
			rate = new Rate();
		}

		return "edit";
	}

	public String save() {
		boolean isNew = (rate.getId() == null);

		rate.setPath(pathManager.get(Long.valueOf(getPathID()).longValue()));

		if (isNew){
			Long newPrice = rate.getPrice();
			List<Rate> rates = rateManager.findRateForTicket(rate);
			if (rates.size() > 0){
				rate = rates.get(0);
				//log.debug("rate existed , and it loadad again instad of save new one !");[OK]
				rate.setPrice(newPrice);
				isNew = false;
			}//else {
				//log.debug("we expect that id is still null !:"+rate.getId());[OK]
			//}
		}

		rateManager.save(rate);

		String key = (isNew) ? "rate.added" : "rate.updated";
		addMessage(key);

		if (isNew) {
			return "list";
		} else {
			return "edit";
		}
	}

	public void setPathManager(PathManager pathManager) {
		this.pathManager = pathManager;
	}

	public void setCarKindManager(CarKindManager carKindManager) {
		this.carKindManager = carKindManager;
	}

	public String getCarKindID() {
		return carKindID;
	}

	public void setCarKindID(String carKindID) {
		this.carKindID = carKindID;
	}

	public String getPathID() {
		return pathID;
	}

	public void setPathID(String pathID) {
		this.pathID = pathID;
	}

	public void setRateManager(RateManager rateManager) {
		this.rateManager = rateManager;
	}

	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}

	public void setId(Long id) {
		this.id = id;
	}

}