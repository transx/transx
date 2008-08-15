/* *Class created on [ Jul 25, 2008 | 9:39:28 PM ] */
package com.asta.app2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.asta.app2.model.enums.Distance;

/**
 * This entity created for calculate the cost of Insurance Sarneshin kind .
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */

@Entity
@Table(name = "insurance_sarneshin")
public class InsuranceSarneshin extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "min_capacity", nullable = false, length = 3)
	private Integer min;

	@Column(name = "max_capacity", nullable = false, length = 3)
	private Integer max;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Distance distance;

	@Column(nullable = false)
	private Long price;

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof InsuranceSarneshin)
			return true;

		final InsuranceSarneshin is = (InsuranceSarneshin) o;
		return (max != null ? max.equals(is.getMax()) : false);
	}

	@Override
	public int hashCode() {
		return (min != null ? min.hashCode() : 0)
				+ (max != null ? max.hashCode() : 0)
				+ (price != null ? price.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getDistance() + "/" + getMin() + ":" + getMax() + "="
				+ getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
}
