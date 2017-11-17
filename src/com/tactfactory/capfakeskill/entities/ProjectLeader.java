package com.tactfactory.capfakeskill.entities;

import java.util.List;

public class ProjectLeader extends User {

	private List<Project> projects;

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}
