package com.tactfactory.capfakeskill.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tactfactory.capfakeskill.dao.base.BaseDAO;
import com.tactfactory.capfakeskill.dao.base.TableField;
import com.tactfactory.capfakeskill.entities.User;

public class UserDAO extends BaseDAO<User> implements IUserDAO {

    @Override
    protected void initTableStructure() {
        this.recordField(new TableField("id", "INT(11)", true));
        this.recordField(new TableField("lastname", "varchar(255)"));
        this.recordField(new TableField("firstname", "varchar(255)"));
        this.recordField(new TableField("email", "varchar(255)"));
        this.recordField(new TableField("password", "varchar(100)"));
    }
    @Override
    protected User generateEntity() {
        return new User();
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
		this.updateColumns = "lastname=?,firstname=?,email=?,password=?";
	}
}
