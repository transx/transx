package com.asta.app2.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Path extends BaseObject implements Comparable<Path> {
	private Long id;
	private Path parent;
	private City start;
	private City end;
	private Long space;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Path))
			return false;

		final Path path = (Path) o;
		return (id != null ? id.equals(path.getId()) : false);
	}

	@Override
	public int hashCode() {
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public String toString() {
		return getStart().getName()
				+ (parent != null ? "_" + parent.getEnd().getName() : "") + "_"
				+ getEnd().getName();
	}

	public int compareTo(Path path) {
		String otherPath = path.toString();
		return this.toString().compareTo(otherPath);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id", nullable = true)
	public Path getParent() {
		return parent;
	}

	public void setParent(Path parent) {
		this.parent = parent;
	}

	@ManyToOne
	@JoinColumn(name = "city_start_id", nullable = true)
	public City getStart() {
		return start;
	}

	public void setStart(City start) {
		this.start = start;
	}

	@ManyToOne
	@JoinColumn(name = "city_end_id", nullable = true)
	public City getEnd() {
		return end;
	}

	public void setEnd(City end) {
		this.end = end;
	}

	// space == fasele
	@Column(length = 20, nullable = true)
	public Long getSpace() {
		return space;
	}

	public void setSpace(Long space) {
		this.space = space;
	}

}
