package airport.dao.dictionary;

import airport.entity.dictionary.Runway;

import java.sql.*;

public class RunwaysDao extends AbstractDictionaryDao<Runway> {

    private static final String TABLE_NAME = "flight_runways";
    private static final String ID_COLUMN = "runway_id";
    private static final String NAME_COLUMN = "runway_number";

    public RunwaysDao(Connection connection) {
        super(connection, TABLE_NAME, ID_COLUMN, NAME_COLUMN);
    }

    @Override
    protected Runway mapRow(ResultSet resultSet) throws SQLException {
        return new Runway(
                resultSet.getInt(ID_COLUMN),
                resultSet.getString(NAME_COLUMN)
        );
    }
}
