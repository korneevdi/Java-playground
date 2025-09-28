package airport.dao.basic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBasicDao<T> {

    protected final Connection connection;
    private final String tableName;
    private final String selectedFields;

    private final String idName;

    public AbstractBasicDao(Connection connection, String tableName, String selectedFields, String idName) {
        this.connection = connection;
        this.tableName = tableName;
        this.selectedFields = selectedFields;
        this.idName = idName;
    }

    // Show the list of elements
    public List<T> findAll() {
        String sql = """
                SELECT %s FROM %s
                """.formatted(selectedFields, tableName);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<T> allElements = new ArrayList<>();
            while (resultSet.next()) {
                allElements.add(mapRow(resultSet));
            }
            return allElements;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find element by id
    public T findById(int id) {
        String sql = """
                SELECT %s FROM %s
                WHERE %s = ?
                """.formatted(selectedFields, tableName, idName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapRow(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<T> findByField(String fieldName, String fieldValue) {
        String sql = """
                SELECT %s FROM %s
                WHERE %s = ?
                """.formatted(selectedFields, tableName, fieldName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fieldValue);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> allContacts = new ArrayList<>();
            while (resultSet.next()) {
                allContacts.add(mapRow(resultSet));
            }
            return allContacts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(T entity) {
        String sql = buildInsertSql();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setInsertStatement(ps, entity);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(T entity) {
        String sql = buildUpdateSql();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setUpdateStatement(ps, entity);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteById(int id) {
        String sql = """
                DELETE FROM %s
                WHERE %s = ?
                """.formatted(tableName, idName);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean exists(T entity) {
        String sql = buildExistsSql();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setExistsStatement(ps, entity);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next(); // 'true' if something found, 'false' otherwise
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Each child DAO class must describe how to map a table row to a Java object
    protected abstract T mapRow(ResultSet resultSet) throws SQLException;

    protected abstract String buildInsertSql();

    protected abstract void setInsertStatement(PreparedStatement ps, T entity) throws SQLException;

    protected abstract String buildUpdateSql();

    protected abstract void setUpdateStatement(PreparedStatement ps, T entity) throws SQLException;

    protected abstract String buildExistsSql();

    protected abstract void setExistsStatement(PreparedStatement ps, T entity) throws SQLException;
}
