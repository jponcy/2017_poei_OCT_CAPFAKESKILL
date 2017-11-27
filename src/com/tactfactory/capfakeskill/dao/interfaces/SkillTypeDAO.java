package com.tactfactory.capfakeskill.dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tactfactory.capfakeskill.dao.base.BaseDAO;
import com.tactfactory.capfakeskill.entities.SkillType;
import com.tactfactory.capfakeskill.exceptions.DatabaseNotReadyException;
import com.tactfactory.capfakeskill.manager.DatabaseManager;

public class SkillTypeDAO extends BaseDAO<SkillType> {

    public SkillTypeDAO() {
		super(SkillType.class);
		// TODO Auto-generated constructor stub
	}

	private static final String SQL_INSERT = "INSERT INTO skill_type (name) VALUES (?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM skill_type";

    /**
     * Inserts many types in one operation.
     * @param types
     * @return
     */
    public int insert(List<SkillType> types) {
        int result = 0;

        try (PreparedStatement st = DatabaseManager.conn().prepareStatement(SQL_INSERT)) {
            types.forEach(type -> {
                try {
                    st.setString(1, type.getName());
                    st.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            int[] counts = st.executeBatch();

            for (int i : counts) {
                result += i;
            }
        } catch (SQLException | DatabaseNotReadyException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<SkillType> findAll() {
        List<SkillType> result = new ArrayList<>();
        Connection conn;

        try {
            conn = DatabaseManager.conn();

            try (PreparedStatement st = conn.prepareStatement(SQL_SELECT_ALL)) {
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("name");
                    int id = rs.getInt("id");

//                    result.add(new SkillType(name).setId(id));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (DatabaseNotReadyException e) {
            e.printStackTrace();
        }

        return result ;
    }

	@Override
	public SkillType insert(SkillType item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCreateTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, String> getTableStructure() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SkillType retreiveDatas(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setPreparedStatement(PreparedStatement st, SkillType item) {
		// TODO Auto-generated method stub

	}
}
