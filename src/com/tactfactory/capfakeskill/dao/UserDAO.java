package com.tactfactory.capfakeskill.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.tactfactory.capfakeskill.dao.base.BaseDAO;
import com.tactfactory.capfakeskill.entities.User;
import com.tactfactory.capfakeskill.entities.base.BaseEntity;
import com.tactfactory.capfakeskill.exceptions.DatabaseNotReadyException;
import com.tactfactory.capfakeskill.manager.DatabaseManager;

public class UserDAO extends BaseDAO<User> implements IUserDAO {

	@Override
	public void delete(User item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User item) {
		// TODO Auto-generated method stub

	}

	@Override
	public User select(User item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 	id        int (11) Auto_increment  NOT NULL ,
	    lastname  Varchar (255) ,
	    firstname Varchar (255) ,
	    email     Varchar (255) ,
	    password  Varchar (100) ,
	    id_carrer_manager   Int ,
	    PRIMARY KEY (id )
	 */
	public String getCreateTable() {
		String result = DatabaseManager.CREATE_TABLE[0] + this.tableName
				+ DatabaseManager.CREATE_TABLE[1]
				+ "id        int (11) Auto_increment  NOT NULL ,"
				+ "lastname  Varchar (255) ," + "firstname Varchar (255) ,"
				+ "email     Varchar (255) ," + "password  Varchar (100) ,"
				+ "id_carrer_manager   Int ," + "PRIMARY KEY (id )"
				+ DatabaseManager.CREATE_TABLE[2];
		return result;
	}

	@Override
	protected void setPreparedStatement(PreparedStatement st, User item) {
		try {
			st.setString(1, null);
			st.setString(2, item.getLastname());
			st.setString(3, item.getFirstname());
			st.setString(4, item.getEmail());
			st.setString(5, item.getPassword());
			st.setString(6, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected User retreiveDatas(ResultSet rs) {
		User result = new User();

		try {
			result.setId(rs.getDouble(1));
			result.setLastname(rs.getString(2));
			result.setFirstname(rs.getString(3));
			result.setEmail(rs.getString(4));
			result.setPassword(rs.getString(5));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public UserDAO() {
		this.tableName = "user";
		this.questionMarks = "?,?,?,?,?,?";
	}
}
