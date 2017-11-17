package com.tactfactory.capfakeskill.entities;

import java.util.Date;
import java.util.List;

import com.tactfactory.capfakeskill.entities.base.BaseEntity;

public class Project extends BaseEntity {

	private String name;
	private Date startAt;
	private Date endAt;
	private Date deadline;
	private String description;
	private List<Skill> skills;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", startAt=" + startAt + ", endAt="
				+ endAt + ", deadline=" + deadline + ", description="
				+ description + ", skills=" + skills + ", getId()=" + getId()
				+ "]";
	}

}
