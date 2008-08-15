/* *Class created on [ Jul 25, 2008 | 9:40:27 PM ] */
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

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
@Table(name = "insurance_badaneh")
public class InsuranceBadaneh extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Quality quality;

	@Column(nullable = false)
	private Boolean jodaganeh;

	@Column(name = "min_km", nullable = false)
	private Long min;

	@Column(name = "max_km", nullable = false)
	private Long max;

	@Column(nullable = false)
	private Long price;

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof InsuranceBadaneh)
			return true;

		final InsuranceBadaneh ib = (InsuranceBadaneh) o;
		return (price != null ? price.equals(ib.getPrice()) : false);
	}

	@Override
	public int hashCode() {
		return (quality != null ? quality.hashCode() : 23)
				+ (jodaganeh != null ? jodaganeh.hashCode() : 23)
				+ (quality != null ? price.hashCode() : 23);
	}

	@Override
	public String toString() {
		return getQuality().getLabel() + "-" + "+" + getJodaganeh()
				+ getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Quality getQuality() {
		return quality;
	}

	public void setQuality(Quality quality) {
		this.quality = quality;
	}

	public Boolean getJodaganeh() {
		return jodaganeh;
	}

	public void setJodaganeh(Boolean jodaganeh) {
		this.jodaganeh = jodaganeh;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
}
