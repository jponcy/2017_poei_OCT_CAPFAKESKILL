package com.tactfactory.capfakeskill.entities.base;

import javax.persistence.Column;
import javax.persistence.Id;

public class BaseEntity {

	@Id
	@Column(name="id")
	private Double id;

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

}
