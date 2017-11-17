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

public class UserDAO extends BaseDAO implements IUserDAO {

	@Override
	public BaseEntity insert(BaseEntity item) {
		User itemUser = (User)item;
		try {
			PreparedStatement st = DatabaseManager.conn()
					.prepareStatement("INSERT INTO User VALUES (?,?,?,?,?,?)");
			st.setString(1, "null");
			st.setString(2, itemUser.getLastname());
			st.setString(3, itemUser.getFirstname());
			st.setString(4, itemUser.getEmail());
			st.setString(5, itemUser.getPassword());
			st.setString(6, "null");

			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();
            if(rs.next())
            {
                item.setId(rs.getDouble(1));
            }

		} catch (DatabaseNotReadyException | SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public void delete(BaseEntity item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(BaseEntity item) {
		// TODO Auto-generated method stub

	}

	@Override
	public BaseEntity select(BaseEntity item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BaseEntity> select() {
		// TODO Auto-generated method stub
		return null;
	}

}
