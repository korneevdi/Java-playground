package airport.dao.dictionary;

import airport.entity.dictionary.Gate;

import java.sql.*;

public class GatesDao extends AbstractDictionaryDao<Gate> {

    private static final String TABLE_NAME = "gates";
    private static final String ID_COLUMN = "gate_id";
    private static final String NAME_COLUMN = "gate_number";

    public GatesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_COLUMN, NAME_COLUMN);
    }

    @Override
    protected Gate mapRow(ResultSet resultSet) throws SQLException {
        return new Gate(
                resultSet.getInt(ID_COLUMN),
                resultSet.getString(NAME_COLUMN)
        );
    }
}
