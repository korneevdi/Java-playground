package airport.dao.dictionary;

import airport.dao.AbstractDao;
import airport.entity.dictionary.Terminal;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class TerminalsDao extends AbstractDao<Terminal> {

    private static final String TABLE_NAME = "terminals";
    private static final String ID_NAME = "terminal_id";
    private final static List<String> ALL_FIELDS = List.of(
            "terminal_number"
    );
    private final static List<String> UNIQUE_FIELDS = ALL_FIELDS;

    public TerminalsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, ALL_FIELDS, UNIQUE_FIELDS);
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
    protected void setInsertStatement(PreparedStatement ps, Terminal terminal) throws SQLException {
        ps.setString(1, terminal.getNumber());
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

    @Override
    public Map<String, String> getColumnToFieldMap() {
        return Map.ofEntries(
                Map.entry("terminal_id", "id"),
                Map.entry("terminal_number", "number")
        );
    }
}
