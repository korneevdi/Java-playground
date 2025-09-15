package airport.dao.dictionary;

import airport.entity.dictionary.Type;

import java.sql.*;

public class TypeDao extends AbstractDictionaryDao<Type> {

    private static final String TABLE_NAME = "types";
    private static final String ID_COLUMN = "type_id";
    private static final String NAME_COLUMN = "type_name";

    public TypeDao(Connection connection) {
        super(connection, TABLE_NAME, ID_COLUMN, NAME_COLUMN);
    }

    @Override
    protected Type mapRow(ResultSet resultSet) throws SQLException {
        return new Type(
                resultSet.getInt(ID_COLUMN),
                resultSet.getString(NAME_COLUMN)
        );
    }
}
