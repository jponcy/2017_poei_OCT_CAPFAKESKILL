package com.tactfactory.capfakeskill.dao.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.tactfactory.capfakeskill.dao.base.BaseDAO;
import com.tactfactory.capfakeskill.dao.interfaces.IUserDAO;
import com.tactfactory.capfakeskill.entities.User;
import com.tactfactory.capfakeskill.manager.DatabaseManager;

public class UserDAO extends BaseDAO<User> implements IUserDAO {

	/**
	 * id int (11) Auto_increment NOT NULL , lastname Varchar (255) , firstname
	 * Varchar (255) , email Varchar (255) , password Varchar (100) ,
	 * id_carrer_manager Int , PRIMARY KEY (id )
	 */
	/*@Override
	public String getCreateTable() {
		String result = DatabaseManager.CREATE_TABLE[0] + this.tableName
				+ DatabaseManager.CREATE_TABLE[1]
				+ "id        int (11) Auto_increment  NOT NULL ,"
				+ "lastname  Varchar (255) ,"
				+ "firstname Varchar (255) ,"
				+ "email     Varchar (255) ,"
				+ "password  Varchar (100) ,"
				// + "group_id  int (11) Auto_increment  NOT NULL ,"
				+ "id_carrer_manager   Int ," + "PRIMARY KEY (id )"
				// + "FOREIGN KEY (group_id) REFERENCES group(id)"
				+ DatabaseManager.CREATE_TABLE[2];
		return result;
	}*/

	@Override
	protected Map<String, String> getTableStructure() {
		Map<String, String> result = new HashMap<>();

		result.put("id", "int");
		result.put("lastname", "string");
		result.put("firstname", "string");
		result.put("email", "string");
		result.put("password", "string");
		result.put("id_carrer_manager", "int");

		return result;
	}

	@Override
	protected void setPreparedStatement(PreparedStatement st, User item) {
		short i = 1;

		try {
			st.setObject((item.getId() == null ? i++ : 5), item.getId());

			st.setString(i++, item.getLastname());
			st.setString(i++, item.getFirstname());
			st.setString(i++, item.getEmail());
			st.setString(i++, item.getPassword());
			if (item.getCarrer_manager() != null) {
				st.setDouble(i++, item.getCarrer_manager().getId());
			}else {
				st.setNull(i++, 0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*@Override
	protected User retreiveDatas(ResultSet rs) {
		User result = new User();

		try {
			result.setId(rs.getDouble(1));
			result.setLastname(rs.getString(2));
			result.setFirstname(rs.getString(3));
			result.setEmail(rs.getString(4));
			result.setPassword(rs.getString(5));
			result.setId_carrer_manager(rs.getInt(6));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}*/

	public UserDAO() {
		super(User.class);
		this.questionMarks = "?,?,?,?,?,?";
		this.updateColumns = "lastname=?,firstname=?,email=?,password=?,id_carrer_manager=?";
	}
}
