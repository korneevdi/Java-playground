package airport.dao.dictionary;

import airport.entity.dictionary.Status;

import java.sql.*;

public class StatusDao extends AbstractDictionaryDao<Status> {

    private static final String TABLE_NAME = "statuses";
    private static final String ID_COLUMN = "status_id";
    private static final String NAME_COLUMN = "status_name";

    public StatusDao(Connection connection) {
        super(connection, TABLE_NAME, ID_COLUMN, NAME_COLUMN);
    }

    @Override
    protected Status mapRow(ResultSet resultSet) throws SQLException {
        return new Status(
                resultSet.getInt(ID_COLUMN),
                resultSet.getString(NAME_COLUMN)
        );
    }
}
