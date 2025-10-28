package airport.dao.dictionary;

import airport.entity.dictionary.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusesDao extends AbstractDictionaryDao<Status> {

    private static final String TABLE_NAME = "statuses";
    private static final String ID_NAME = "status_id";
    private final static List<String> UNIQUE_FIELDS = new ArrayList<>() {{
        add("status_name");
    }};

    public StatusesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
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
    protected String buildExistsSql() {
        return """
                SELECT 1 FROM %s
                WHERE %s = ?
                """.formatted(TABLE_NAME, UNIQUE_FIELDS.get(0));
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, Status status) throws SQLException {
        ps.setString(1, status.getName());
    }

    @Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (status_name) VALUES
                (?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Status status) throws SQLException {
        ps.setString(1, status.getName());
    }

    @Override
    protected Status mapRow(ResultSet resultSet) throws SQLException {
        return new Status(
                resultSet.getInt("status_id"),
                resultSet.getString("status_name")
        );
    }
}
