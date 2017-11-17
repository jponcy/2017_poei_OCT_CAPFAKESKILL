package com.tactfactory.capfakeskill.entities;

import java.util.List;

public class CarrerManager extends User {

	private List<Collaborator> collaborator;

	public List<Collaborator> getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(List<Collaborator> collaborator) {
		this.collaborator = collaborator;
	}

}
