package com.tactfactory.capfakeskill.dao.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tactfactory.capfakeskill.dao.base.BaseDAO;
import com.tactfactory.capfakeskill.dao.interfaces.IProjectDAO;
import com.tactfactory.capfakeskill.entities.Project;
import com.tactfactory.capfakeskill.manager.DatabaseManager;

public class ProjectDAO extends BaseDAO<Project> implements IProjectDAO {



	@Override
	public void delete(Project item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Project item) {
		// TODO Auto-generated method stub

	}

	@Override
	public Project select(Project item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 	id          int (11) Auto_increment  NOT NULL ,
	    name        Varchar (255) ,
	    startAt     Date ,
	    description Text ,
	    endAt       Date ,
	    deadline    Date ,
	    id_User     Int ,
	    PRIMARY KEY (id ) ,
	    UNIQUE (name )
	 */
	public String getCreateTable() {
		String result = DatabaseManager.CREATE_TABLE[0] + this.tableName
				+ DatabaseManager.CREATE_TABLE[1]
				+ "id          int (11) Auto_increment  NOT NULL ,"
				+ "name        Varchar (255) ," + "startAt     Date ,"
				+ "description Text ," + "endAt       Date ,"
				+ "deadline    Date ," + "id_User     Int ,"
				+ "PRIMARY KEY (id ) ," + "UNIQUE (name )"
				+ DatabaseManager.CREATE_TABLE[2];
		return result;
	}

	@Override
	protected void setPreparedStatement(PreparedStatement st, Project item) {
		try {
			st.setString(1, null);
			st.setString(2, item.getName());
			st.setDate(3, item.getStartAt() != null ? new java.sql.Date(
					item.getStartAt().getTime()) : null);
			st.setString(4, item.getDescription());
			st.setDate(5, item.getEndAt() != null ? new java.sql.Date(
					item.getEndAt().getTime()) : null);
			st.setDate(6,
					item.getDeadline() != null ? new java.sql.Date(
							item.getDeadline().getTime()) : null);
			st.setString(7, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ProjectDAO() {
		super(Project.class);
		this.tableName = "project";
		this.questionMarks = "?,?,?,?,?,?,?";
	}

	@Override
	protected Project retreiveDatas(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, String> getTableStructure() {
		// TODO Auto-generated method stub
		return null;
	}


}
