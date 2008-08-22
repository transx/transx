/* *Class created on [ Jun 10, 2008 | 7:55:10 AM ] */
package com.asta.app2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
@Entity
@Table(name = "chair")
public class Chair extends BaseObject implements Comparable<Chair>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Chair))
			return false;

		final Chair chair = (Chair) o;
		return (id != null ? id.equals(chair.getId()) : false);
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getId().toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int compareTo(Chair chair) {
		String otherChair = chair.toString();
		return this.toString().compareTo(otherChair);
	}
}
