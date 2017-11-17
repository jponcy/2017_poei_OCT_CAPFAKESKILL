package com.tactfactory.capfakeskill.entities;

import java.util.Date;
import java.util.List;

public class Grading {

	private Project project;
	private List<CollaboratorSkills> skillsGoal;
	private ProjectLeader leader;
	private Collaborator collaborator;
	private Integer goal;
	private Boolean succes;
	private Date startAt;
	private Date endAt;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<CollaboratorSkills> getSkillsGoal() {
		return skillsGoal;
	}

	public void setSkillsGoal(List<CollaboratorSkills> skillsGoal) {
		this.skillsGoal = skillsGoal;
	}

	public ProjectLeader getLeader() {
		return leader;
	}

	public void setLeader(ProjectLeader leader) {
		this.leader = leader;
	}

	public Collaborator getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
	}

	public Integer getGoal() {
		return goal;
	}

	public void setGoal(Integer goal) {
		this.goal = goal;
	}

	public Boolean getSucces() {
		return succes;
	}

	public void setSucces(Boolean succes) {
		this.succes = succes;
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
