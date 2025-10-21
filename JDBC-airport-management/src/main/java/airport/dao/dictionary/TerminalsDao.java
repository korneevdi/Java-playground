package airport.dao.dictionary;

import airport.entity.dictionary.Terminal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TerminalsDao extends AbstractDictionaryDao<Terminal> {

    private static final String TABLE_NAME = "terminals";
    private static final String ID_NAME = "terminal_id";
    private final static List<String> UNIQUE_FIELDS = new ArrayList<>() {{
        add("terminal_number");
    }};

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
    protected String buildExistsSql() {
        return """
                SELECT 1 FROM %s
                WHERE %s = ?
                """.formatted(TABLE_NAME, UNIQUE_FIELDS.get(0));
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, Terminal terminal) throws SQLException {
        ps.setString(1, terminal.getNumber());
    }

    @Override
    protected Terminal mapRow(ResultSet resultSet) throws SQLException {
        return new Terminal(
                resultSet.getInt("terminal_id"),
                resultSet.getString("terminal_number")
        );
    }
}
