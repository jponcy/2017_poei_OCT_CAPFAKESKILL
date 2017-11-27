package com.tactfactory.capfakeskill.entities.proxy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import sun.security.pkcs11.Secmod.DbMode;

import com.tactfactory.capfakeskill.dao.dao.UserDAO;
import com.tactfactory.capfakeskill.dao.interfaces.SkillTypeDAO;
import com.tactfactory.capfakeskill.entities.Collaborator;
import com.tactfactory.capfakeskill.entities.CollaboratorSkills;
import com.tactfactory.capfakeskill.entities.Skill;
import com.tactfactory.capfakeskill.entities.User;
import com.tactfactory.capfakeskill.exceptions.DatabaseNotReadyException;
import com.tactfactory.capfakeskill.manager.DatabaseManager;

public class ProxyUserSkill {

	private User user;
	private List<CollaboratorSkills> skills;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<CollaboratorSkills> getSkills() {
		return skills;
	}
	public void setSkills(List<CollaboratorSkills> skills) {
		this.skills = skills;
	}

	public Collaborator getSkillsForCollaborator(Double userId){
		Collaborator collaborator = new Collaborator();
		SkillTypeDAO skillTypeDAO = new SkillTypeDAO();
		UserDAO userDAO = new UserDAO();

		user = userDAO.select(userId);

		String request = "SELECT (skill.id, skill ...) FROM skill "
				+ "INNER JOIN grading ON skill.id = grading.id_skill "
				+ "INNER JOIN user ON grading.id_user = user.id"
				+ "WHERE user.id = ?";

		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(request);

			while(rs.next()){
				Skill skill = new Skill();
				skill.setId(rs.getDouble(1));
				skill.setName(rs.getString(2));
				skill.setType(skillTypeDAO.select(rs.getDouble(3)));

				CollaboratorSkills colSkill = new CollaboratorSkills();
				colSkill.setSkill(skill);
				colSkill.setLevel(rs.getInt(4));

				skills.add(colSkill);
			}

		} catch (DatabaseNotReadyException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!skills.isEmpty()) {
			((Collaborator)user).setSkills(skills);
			return ((Collaborator)user);
		}

		return null;
	}
}
