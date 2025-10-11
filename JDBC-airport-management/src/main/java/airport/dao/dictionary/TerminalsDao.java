package airport.dao.dictionary;

import airport.entity.dictionary.Terminal;

import java.sql.*;

public class TerminalsDao extends AbstractDictionaryDao<Terminal> {

    private static final String TABLE_NAME = "terminals";
    private static final String ID_NAME = "terminal_id";

    public TerminalsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT ter.terminal_id, ter.terminal_number
                FROM terminals ter
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT ter.terminal_id, ter.terminal_number
                FROM terminals ter
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT ter.terminal_id, ter.terminal_number
                FROM terminals ter
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected Terminal mapRow(ResultSet resultSet) throws SQLException {
        return new Terminal(
                resultSet.getInt("terminal_id"),
                resultSet.getString("terminal_number")
        );
    }
}
