package com.tactfactory.capfakeskill.entities;

import com.tactfactory.capfakeskill.entities.base.BaseEntity;

public class Skill extends BaseEntity {

	private String name;
	private SkillType type;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SkillType getType() {
		return type;
	}
	public void setType(SkillType type) {
		this.type = type;
	}


}
