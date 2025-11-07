package airport.dao.dictionary;

import airport.dao.AbstractDao;
import airport.entity.dictionary.Runway;

import java.sql.*;
import java.util.List;

public class RunwaysDao extends AbstractDao<Runway> {

    private static final String TABLE_NAME = "flight_runways";
    private static final String ID_NAME = "runway_id";
    private final static List<String> ALL_FIELDS = List.of(
            "runway_number"
    );
    private final static List<String> UNIQUE_FIELDS = ALL_FIELDS;

    public RunwaysDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, ALL_FIELDS, UNIQUE_FIELDS);
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
    protected void setExistsStatement(PreparedStatement ps, Runway runway) throws SQLException {
        ps.setString(1, runway.getNumber());
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Runway runway) throws SQLException {
        ps.setString(1, runway.getNumber());
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Runway runway) throws SQLException {
        ps.setString(1, runway.getNumber());
        ps.setInt(2, runway.getId());
    }

    @Override
    protected Runway mapRow(ResultSet resultSet) throws SQLException {
        return new Runway(
                resultSet.getInt("runway_id"),
                resultSet.getString("runway_number")
        );
    }
}
