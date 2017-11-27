package com.tactfactory.capfakeskill.entities;

import java.util.List;

public class CarrerManager extends User {

	private List<Collaborator> collaborators;

	public List<Collaborator> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<Collaborator> collaborator) {
		this.collaborators = collaborator;
	}

}
