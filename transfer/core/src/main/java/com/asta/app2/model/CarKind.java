/* *Class created on [ Jun 1, 2008 | 4:52:52 PM ] */
package com.asta.app2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.asta.app2.model.enums.Quality;
import com.asta.app2.model.enums.CarType;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
@Table(name = "car_kind")
public class CarKind extends BaseObject implements Comparable<CarKind> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer capacity;

	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = true)
	private Quality quality;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = true)
	private CarType type;

	public CarKind() {}
	
	public CarKind(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof CarKind)
			return true;

		final CarKind carKind = (CarKind) o;
		return (id != null ? id.equals(carKind.getId()) : false);
	}

	@Override
	public int hashCode() {
		return (name != null ? name.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getName()+" "+getCapacity();
	}

	public int compareTo(CarKind carKind) {
		String otherCarKind = carKind.toString();
		return this.toString().compareTo(otherCarKind);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Quality getQuality() {
		return quality;
	}

	public void setQuality(Quality quality) {
		this.quality = quality;
	}
	public CarType getType() {
		return type;
	}
	
	public void setType(CarType type) {
		this.type = type;
	}


}

