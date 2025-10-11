package airport.dao.dictionary;

import airport.entity.dictionary.Status;

import java.sql.*;

public class StatusesDao extends AbstractDictionaryDao<Status> {

    private static final String TABLE_NAME = "statuses";
    private static final String ID_NAME = "status_id";

    public StatusesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
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
    protected Status mapRow(ResultSet resultSet) throws SQLException {
        return new Status(
                resultSet.getInt("status_id"),
                resultSet.getString("status_name")
        );
    }
}
