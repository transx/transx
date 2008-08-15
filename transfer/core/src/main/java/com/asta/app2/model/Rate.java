/* *Class created on [ Jul 21, 2008 | 5:32:43 PM ] */
package com.asta.app2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.asta.app2.model.enums.Quality;
import com.asta.app2.model.enums.RateType;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
public class Rate extends BaseObject implements Comparable<Rate> {
	private static final long serialVersionUID = 3580667989356567326L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "rate_type", length = 20, nullable = false)
	private RateType rateType;

	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	private Quality quality;

	@ManyToOne
	@JoinColumn(name = "path_id", nullable = false)
	private Path path;
	
	@Column(nullable = false)
	private Long price;

	@Column(nullable = false)
	private Long toll;
	
	@Column(nullable = false)
	private Long snack;
	
	public Rate() {
	}

	public Rate(RateType rateType, Path path, Quality quality) {
		this.rateType = rateType;
		this.path = path;
		this.quality = quality;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof Rate)
			return true;

		final Rate rate = (Rate) o;
		return (price != null ? price.equals(rate.getPrice()) : false);
	}

	@Override
	public int hashCode() {
		int hash = 23 + (quality != null ? quality.hashCode() : 0)
				+ (path != null ? path.hashCode() : 0) + 23
				+ (rateType != null ? rateType.hashCode() : 0)
				+ (price != null ? price.hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		return getRateType().getLabel() + quality.getLabel()
				+ getPath().toString();
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

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public RateType getRateType() {
		return rateType;
	}

	public void setRateType(RateType rateType) {
		this.rateType = rateType;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public int compareTo(Rate rate) {
		String anotherRate = rate.toString();
		return this.toString().compareTo(anotherRate);
	}

	public Long getToll() {
		return toll;
	}

	public void setToll(Long toll) {
		this.toll = toll;
	}

	public Long getSnack() {
		return snack;
	}

	public void setSnack(Long snack) {
		this.snack = snack;
	}

}
