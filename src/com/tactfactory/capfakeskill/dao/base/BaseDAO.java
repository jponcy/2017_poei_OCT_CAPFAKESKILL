package com.tactfactory.capfakeskill.dao.base;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tactfactory.capfakeskill.entities.User;
import com.tactfactory.capfakeskill.entities.base.BaseEntity;
import com.tactfactory.capfakeskill.exceptions.DatabaseNotReadyException;
import com.tactfactory.capfakeskill.manager.DatabaseManager;
import com.tactfactory.capfakeskill.utils.DumpFields;

public abstract class BaseDAO<K extends BaseEntity> implements IBaseDAO<K> {

    /** Flag to build SQL query to delete data by id. */
	private static final String SQL_DELETE = "DELETE FROM %s WHERE %s=?";
	private static final String SQL_UPDATE = "UPDATE %s SET %s WHERE id = ?";

	abstract protected Map<String, String> getTableStructure();

    protected String questionMarks;

	protected String tableName;

	protected String idColumn = "id";

	protected String updateColumns = null;
	private Class<K> klazz;

	public String getTableName() {
		return tableName;
	}

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

	protected K retreiveDatas(ResultSet rs){
		K result = DumpFields.createContentsEmpty(klazz);

		try {
			List<Field> fields = DumpFields.getFields(klazz);
			List<Method> methods = DumpFields.getSetter(klazz);
			for (Field field : fields) {
				if (!field.getType().isArray()) {
					int columId = rs.findColumn(field.getName());
					String methodName = "set"+
							String.valueOf(field.getName().charAt(0)).toUpperCase()
							+field.getName().substring(1, field.getName().length());
					for (Method method : methods) {
						if (method != null && method.getName().equals(methodName))
						{
							if (field.getType().getSimpleName().equals("Double")) {
								method.invoke(result,rs.getDouble(columId));
							}else if (field.getType().getSimpleName().equals("String")) {
								method.invoke(result,rs.getString(columId));
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return result;
	}

	protected abstract void setPreparedStatement(PreparedStatement st, K item);

	@Override
	public String getCreateTable() {
		String result = DatabaseManager.CREATE_TABLE[0]
				+ (klazz.getAnnotation(javax.persistence.Table.class)).name()
				+ DatabaseManager.CREATE_TABLE[1];

		List<Field> fields = DumpFields.getFields(klazz);
		for (Field field : fields) {
			if (field.getAnnotation(javax.persistence.Column.class) != null) {
				result += field.getAnnotation(javax.persistence.Column.class).name();
				result += " ";
				if (field.getType().getSimpleName().equals("Double")) {
					result += "int";
				}else if (field.getType().getSimpleName().equals("String")) {
					result += "varchar(255)";
				}else if (field.getType().getSuperclass().getClass().isInstance(BaseEntity.class)) {
					result += "int";
				}

				if (field.getAnnotation(javax.persistence.Id.class) != null) {
					result += " PRIMARY KEY Auto_increment  NOT NULL ";
				}

				result +=",";
			}
		}

		result = result.substring(0, result.length() - 1);
		result += DatabaseManager.CREATE_TABLE[2];

		return result;
	}

	public BaseDAO(Class<K> klazz){
		this.klazz = klazz;
	}
}
