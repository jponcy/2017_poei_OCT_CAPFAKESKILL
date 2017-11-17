package com.tactfactory.capfakeskill.entities;

import java.util.Date;
import java.util.List;

import com.tactfactory.capfakeskill.entities.base.BaseEntity;

public class Grading extends BaseEntity {

	private List<CollaboratorSkills> skillsGoal;
	private ProjectLeader leader;
	private Collaborator collaborator;
	private Boolean succes;
	private Date startAt;
	private Date endAt;

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
