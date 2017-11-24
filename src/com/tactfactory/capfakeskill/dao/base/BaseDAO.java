package com.tactfactory.capfakeskill.dao.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tactfactory.capfakeskill.entities.base.BaseEntity;
import com.tactfactory.capfakeskill.exceptions.DatabaseNotReadyException;
import com.tactfactory.capfakeskill.manager.DatabaseManager;

public abstract class BaseDAO<K extends BaseEntity> implements IBaseDAO<K> {

    /** Flag to build SQL query to delete data by id. */
	private static final String SQL_DELETE = "DELETE FROM %s WHERE %s=?";
	private static final String SQL_UPDATE = "UPDATE %s SET %s WHERE id = ?";


	public BaseDAO() {
	    this.initTableStructure();
    }

    /** Table structure. */
    Map<String, TableField> tableFields = new HashMap<>();
    /** Initialize the specific table structure. */
	abstract protected void initTableStructure();
	/** Record one table field. */
	protected void recordField(TableField description) {
	    this.tableFields.put(description.getName(), description);
	}

    protected String questionMarks;

	protected String tableName;

	protected String idColumn = "id";

	protected String updateColumns = null;

	public String getTableName() {
		return tableName;
	}

	@Override
    public String getCreateTable() {
//        String result = DatabaseManager.CREATE_TABLE[0] + this.tableName
//                + DatabaseManager.CREATE_TABLE[1]
//                + "id        int (11) Auto_increment  NOT NULL ,"
//                + "lastname  Varchar (255) ," + "firstname Varchar (255) ,"
//                + "email     Varchar (255) ," + "password  Varchar (100) ,"
////              + "group_id  int (11) Auto_increment  NOT NULL ,"
//                + "id_carrer_manager   Int ," + "PRIMARY KEY (id )"
////              + "FOREIGN KEY (group_id) REFERENCES group(id)"
//                + DatabaseManager.CREATE_TABLE[2];
//        return result;
	    String result = "";

	    for (Entry<String, TableField> entry : this.tableFields.entrySet()) {
	        if (!result.equals("")) {
	            result += ",\n";
	        }

	        result += entry.getValue().getCreationLine();
	    }

	    result = DatabaseManager.CREATE_TABLE[0] + this.tableName
	              + DatabaseManager.CREATE_TABLE[1]
                  + result + DatabaseManager.CREATE_TABLE[2];

	    System.out.println(result);

	    return result;
    }

	abstract protected K generateEntity();

	/** Upper the first letter. */
	protected String capitalize(String s) {
	    return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}

    protected K retreiveDatas(ResultSet rs) {
        K result = this.generateEntity();
        int i = 1;

        for (Entry<String, TableField> entry : this.tableFields.entrySet()) {
            final String type = capitalize(entry.getValue().getType());
            final String name = capitalize(entry.getValue().getName());

            try {
                Method getter = rs.getClass().getDeclaredMethod(
                        "get" + type, Integer.class);
                Method setter = result.getClass().getDeclaredMethod(
                        "set" + name, Class.forName("java.lang." + type));

                Object value = getter.invoke(rs, i++);
                setter.invoke(result, value);
            } catch (NoSuchMethodException | SecurityException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return result;
    }













	// ***** OLD *****

    @Override
    public void update(K item) {
        final String sql = String.format(BaseDAO.SQL_UPDATE, this.tableName, this.updateColumns);

        try (PreparedStatement ps = DatabaseManager.conn().prepareStatement(sql)) {
            setPreparedStatement(ps, item);
            ps.executeUpdate();
        } catch (SQLException | DatabaseNotReadyException e) {
            e.printStackTrace();
        }
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

	/**
	 * DELETE FROM <table> WHERE id=?
	 */
	@Override
	public void delete(K item) {
	    try {
            Connection conn = DatabaseManager.conn();

            try (PreparedStatement ps = conn.prepareStatement(this.getDeleteQuery())) {
                // Customize the statement.
                ps.setDouble(1, item.getId());

                // Real execution of statement.
                int count = ps.executeUpdate();

                // Check result => print error message if not exactly one row deleted.
                if (count != 1) {
                    System.err.println("Bad number of updated rows --- " + count);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (DatabaseNotReadyException e) {
            e.printStackTrace();
        }
	}

	/** Gets the SQL query to delete one item by id. */
	public String getDeleteQuery() {
        return String.format(BaseDAO.SQL_DELETE, this.tableName, this.idColumn);
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

    @Override
    public K select(K item) {
        return this.select(item.getId());
    }
    @Override
    public K select(double id) {
        K result = null;

        try (PreparedStatement ps = DatabaseManager.conn().prepareStatement("SELECT * FROM " + this.tableName + " WHERE id=?")) {
            ps.setDouble(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = this.retreiveDatas(rs);
            }
        } catch (SQLException | DatabaseNotReadyException e) {
            e.printStackTrace();
        }

        return result;
    }

	protected abstract void setPreparedStatement(PreparedStatement st, K item);
}
