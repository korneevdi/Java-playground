package airport.dao.dictionary;

import airport.entity.dictionary.Terminal;

import java.sql.*;
import java.util.List;

public class TerminalsDao extends AbstractDictionaryDao<Terminal> {

    private static final String TABLE_NAME = "terminals";
    private static final String ID_NAME = "terminal_id";
    private final static List<String> UNIQUE_FIELDS = List.of(
            "terminal_number"
    );

    public TerminalsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
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
    protected void setExistsStatement(PreparedStatement ps, Terminal terminal) throws SQLException {
        ps.setString(1, terminal.getNumber());
    }

    @Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (terminal_number) VALUES
                (?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Terminal terminal) throws SQLException {
        ps.setString(1, terminal.getNumber());
    }

    @Override
    protected String buildUpdateSql() {
        return """
                UPDATE %s
                SET terminal_number = ?
                WHERE %s = ?
                """.formatted(TABLE_NAME, ID_NAME);
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Terminal terminal) throws SQLException {
        ps.setString(1, terminal.getNumber());
        ps.setInt(2, terminal.getId());
    }

    @Override
    protected Terminal mapRow(ResultSet resultSet) throws SQLException {
        return new Terminal(
                resultSet.getInt("terminal_id"),
                resultSet.getString("terminal_number")
        );
    }
}
