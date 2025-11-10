package airport.dao.dictionary;

import airport.dao.AbstractDao;
import airport.entity.dictionary.Status;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class StatusesDao extends AbstractDao<Status> {

    private static final String TABLE_NAME = "statuses";
    private static final String ID_NAME = "status_id";
    private final static List<String> ALL_FIELDS = List.of(
            "status_name"
    );
    private final static List<String> UNIQUE_FIELDS = ALL_FIELDS;

    public StatusesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, ALL_FIELDS, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT st.status_id, st.status_name
                FROM statuses st
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT st.status_id, st.status_name
                FROM statuses st
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT st.status_id, st.status_name
                FROM statuses st
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, Status status) throws SQLException {
        ps.setString(1, status.getName());
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Status status) throws SQLException {
        ps.setString(1, status.getName());
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Status status) throws SQLException {
        ps.setString(1, status.getName());
        ps.setInt(2, status.getId());
    }

    @Override
    protected Status mapRow(ResultSet resultSet) throws SQLException {
        return new Status(
                resultSet.getInt("status_id"),
                resultSet.getString("status_name")
        );
    }

    @Override
    public Map<String, String> getColumnToFieldMap() {
        return Map.ofEntries(
                Map.entry("status_id", "id"),
                Map.entry("status_name", "name")
        );
    }
}
