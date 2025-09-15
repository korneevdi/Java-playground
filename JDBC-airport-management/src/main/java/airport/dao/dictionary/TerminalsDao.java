package airport.dao.dictionary;

import airport.entity.dictionary.Terminal;

import java.sql.*;

public class TerminalsDao extends AbstractDictionaryDao<Terminal> {

    private static final String TABLE_NAME = "terminals";
    private static final String ID_COLUMN = "terminal_id";
    private static final String NAME_COLUMN = "terminal_number";

    public TerminalsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_COLUMN, NAME_COLUMN);
    }

    @Override
    protected Terminal mapRow(ResultSet resultSet) throws SQLException {
        return new Terminal(
                resultSet.getInt(ID_COLUMN),
                resultSet.getString(NAME_COLUMN)
        );
    }
}
