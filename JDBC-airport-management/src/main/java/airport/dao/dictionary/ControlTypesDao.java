package airport.dao.dictionary;

import airport.entity.dictionary.ControlType;

import java.sql.*;

public class ControlTypesDao extends AbstractDictionaryDao<ControlType> {

    private static final String TABLE_NAME = "control_types";
    private static final String ID_COLUMN = "control_type_id";
    private static final String NAME_COLUMN = "control_type";

    public ControlTypesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_COLUMN, NAME_COLUMN);
    }

    @Override
    protected ControlType mapRow(ResultSet resultSet) throws SQLException {
        return new ControlType(
                resultSet.getInt(ID_COLUMN),
                resultSet.getString(NAME_COLUMN)
        );
    }
}
