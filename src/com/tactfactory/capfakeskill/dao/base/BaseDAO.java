package com.tactfactory.capfakeskill.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tactfactory.capfakeskill.entities.SkillType;
import com.tactfactory.capfakeskill.entities.base.BaseEntity;
import com.tactfactory.capfakeskill.exceptions.DatabaseNotReadyException;
import com.tactfactory.capfakeskill.manager.DatabaseManager;

public abstract class BaseDAO<K extends BaseEntity> implements IBaseDAO<K> {

	protected String questionMarks;

	protected String tableName;

	public String getTableName() {
		return tableName;
	}

	@Override
	public K insert(K item) {

		try {
			PreparedStatement st = DatabaseManager.conn().prepareStatement(
					"INSERT INTO " + this.tableName + " VALUES ("
							+ this.questionMarks + ")",// Check element
					// number of
					// items
					PreparedStatement.RETURN_GENERATED_KEYS);

			setPreparedStatement(st, item);

			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				item.setId(rs.getDouble(1));
			}

		} catch (DatabaseNotReadyException | SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public List<K> select() {
		List<K> result = new ArrayList<K>();
		Connection conn;

		try {
			conn = DatabaseManager.conn();

			try (PreparedStatement st = conn.prepareStatement("SELECT * FROM "
					+ DatabaseManager.DATABASE_NAME + "." + this.tableName)) {
				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					result.add(retreiveDatas(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (DatabaseNotReadyException e) {
			e.printStackTrace();
		}

		return result;
	}

	protected abstract K retreiveDatas(ResultSet rs);

	protected abstract void setPreparedStatement(PreparedStatement st, K item);
}
