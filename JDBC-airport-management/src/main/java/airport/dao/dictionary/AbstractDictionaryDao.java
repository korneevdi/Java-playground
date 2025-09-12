// This abstract DAO class is used to avoid code duplication, since all 10 dictionary tables have very similar properties.

package airport.dao.dictionary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDictionaryDao<T> {

    protected final Connection connection;
    private final String tableName;
    private final String idColumn;
    private final String nameColumn;

    public AbstractDictionaryDao(Connection connection, String tableName, String idColumn, String nameColumn) {
        this.connection = connection;
        this.tableName = tableName;
        this.idColumn = idColumn;
        this.nameColumn = nameColumn;
    }

    // Show the list of elements
    public List<T> findAll() {
        String sql =
                """
                SELECT %s, %s FROM %s
                """.formatted(idColumn, nameColumn, tableName);

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
        String sql =
                """
                SELECT %sб %s FROM %s
                WHERE %s = ?
                """.formatted(idColumn, nameColumn, tableName, idColumn);

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

    // Find element by name (element names are unique)
    public T findByName(String elementName) {
        String sql =
                """
                SELECT %s, %s FROM %s
                WHERE %s = ?
                """.formatted(idColumn, nameColumn, tableName, nameColumn);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, elementName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapRow(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add new element
    public void insert(String newElementName) {
        String sql =
                """
                INSERT INTO %s (%s) VALUES
                (?)
                """.formatted(tableName, nameColumn);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newElementName);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update element
    public void update(int id, String newElementName) {
        String sql =
                """
                UPDATE %s
                SET %s = ?
                WHERE %s = ?
                """.formatted(tableName, nameColumn, idColumn);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newElementName);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete element by id
    public boolean deleteById(int id) {
        String sql =
                """
                DELETE FROM %s
                WHERE %s = ?
                """.formatted(tableName, idColumn);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete element by name
    public boolean deleteByName(String name) {
        String sql =
                """
                DELETE FROM %s
                WHERE %s = ?
                """.formatted(tableName, nameColumn);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Each child DAO class must describe how to map a table row to a Java object
    protected abstract T mapRow(ResultSet resultSet) throws SQLException;
}
