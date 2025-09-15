package airport.dao.dictionary;

import airport.entity.dictionary.CheckInCounter;

import java.sql.*;

public class CheckInCountersDao extends AbstractDictionaryDao<CheckInCounter> {

    private static final String TABLE_NAME = "check_in_counters";
    private static final String ID_COLUMN = "counter_id";
    private static final String NAME_COLUMN = "counter_name";

    public CheckInCountersDao(Connection connection) {
        super(connection, TABLE_NAME, ID_COLUMN, NAME_COLUMN);
    }

    @Override
    protected CheckInCounter mapRow(ResultSet resultSet) throws SQLException {
        return new CheckInCounter(
                resultSet.getInt(ID_COLUMN),
                resultSet.getString(NAME_COLUMN)
        );
    }
}
