package airport.dao.basic;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractBasicDao<T> {

    protected final Connection connection;
    private final String tableName;
    private final String idName;
    private final List<String> uniqueFields;

    public AbstractBasicDao(Connection connection, String tableName, String idName, List<String> uniqueFields) {
        this.connection = connection;
        this.idName = idName;
        this.tableName = tableName;
        this.uniqueFields = uniqueFields;
    }

    // Show the list of elements
    public List<T> findAll() {
        String sql = buildFindAllSql();

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

    // Find ID by unique field(s)
    public OptionalInt findId(Map<String, String> uniqueFields) {
        String where = uniqueFields.keySet().stream()
                .map(s -> s + " = ?")
                .collect(Collectors.joining(" AND "));

        String sql = "SELECT " + idName + " FROM " + tableName + " WHERE " + where;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int i = 1;
            for (String value : uniqueFields.values()) {
                ps.setString(i++, value);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return OptionalInt.of(rs.getInt(idName));
            } else {
                return OptionalInt.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find element by id
    public Optional<T> findById(int id) {
        String sql = buildFindByIdSql();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapRow(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find element(s) by field
    public List<T> findByField(String fieldName, Object fieldValue) {
        String sql = buildFindByFieldSql(fieldName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if(fieldValue instanceof String) {
                preparedStatement.setString(1, (String) fieldValue);
            } else if(fieldValue instanceof java.sql.Date) {
                preparedStatement.setDate(1, (java.sql.Date) fieldValue);
            } else if (fieldValue instanceof Integer) {
                preparedStatement.setInt(1, (Integer) fieldValue);
            } else {
                throw new RuntimeException("Unknown value type for 'fieldValue'");
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> allElements = new ArrayList<>();
            while (resultSet.next()) {
                allElements.add(mapRow(resultSet));
            }
            return allElements;
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

    public void insert(T entity) {
        String sql = buildInsertSql();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setInsertStatement(ps, entity);
            ps.executeUpdate();
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

    protected String buildExistsSql() {
        String whereClause = uniqueFields.stream()
                .map(f -> f + " = ?")
                .collect(Collectors.joining(" OR "));

        return "SELECT 1 FROM " + tableName + " WHERE " + whereClause;
    }

    protected abstract String buildFindAllSql();

    protected abstract String buildFindByIdSql();

    protected abstract String buildFindByFieldSql(String fieldName);

    protected abstract void setExistsStatement(PreparedStatement ps, T entity) throws SQLException;

    protected abstract String buildInsertSql();

    protected abstract void setInsertStatement(PreparedStatement ps, T entity) throws SQLException;

    protected abstract T mapRow(ResultSet resultSet) throws SQLException;

    /*
    public boolean update(T entity) {
        String sql = buildUpdateSql();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setUpdateStatement(ps, entity);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract String buildUpdateSql();

    protected abstract void setUpdateStatement(PreparedStatement ps, T entity) throws SQLException;
    */
}
