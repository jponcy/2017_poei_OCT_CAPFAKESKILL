package com.tactfactory.capfakeskill.entities;

import java.util.Date;
import java.util.List;

public class Collaborator extends User {

	private Project project;
	private Date startAt;
	private Date endAt;
	private List<CollaboratorSkills> skills;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public List<CollaboratorSkills> getSkills() {
		return skills;
	}

	public void setSkills(List<CollaboratorSkills> skills) {
		this.skills = skills;
	}

}
