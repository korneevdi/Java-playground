package airport.dao.dictionary;

import airport.entity.dictionary.Runway;

import java.sql.*;

public class RunwaysDao extends AbstractDictionaryDao<Runway> {

    private static final String TABLE_NAME = "flight_runways";
    private static final String ID_NAME = "runway_id";

    public RunwaysDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT fr.runway_id, fr.runway_number
                FROM flight_runways fr
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT fr.runway_id, fr.runway_number
                FROM flight_runways fr
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT fr.runway_id, fr.runway_number
                FROM flight_runways fr
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected Runway mapRow(ResultSet resultSet) throws SQLException {
        return new Runway(
                resultSet.getInt("runway_id"),
                resultSet.getString("runway_number")
        );
    }
}
