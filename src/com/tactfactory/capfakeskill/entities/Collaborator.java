package com.tactfactory.capfakeskill.entities;

import java.util.Date;

public class Collaborator extends User {

	private Project project;
	private Date startAt;
	private Date endAt;

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
}
