package airport.dao.basic;

import airport.entity.basic.Airport;

import java.sql.*;

public class AirportDao extends AbstractBasicDao<Airport> {

    private final static String TABLE_NAME = "airports";
    private final static String ID_NAME = "airport_id";

    public AirportDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT a.airport_id, a.iata, a.icao, a.name, a.city, a.country, a.timezone
                FROM airports a
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT a.airport_id, a.iata, a.icao, a.name, a.city, a.country, a.timezone
                FROM airports a
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT a.airport_id, a.iata, a.icao, a.name, a.city, a.country, a.timezone
                FROM airports a
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected Airport mapRow(ResultSet resultSet) throws SQLException {
        return new Airport(
                resultSet.getInt("airport_id"),
                resultSet.getString("iata"),
                resultSet.getString("icao"),
                resultSet.getString("name"),
                resultSet.getString("city"),
                resultSet.getString("country"),
                resultSet.getString("timezone")
        );
    }

    /*
    // Add new element
    @Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (iata, icao, name, city, country, timezone) VALUES
                (?, ?, ?, ?, ?, ?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Airport airport) throws SQLException {
        ps.setString(1, airport.getIata());
        ps.setString(2, airport.getIcao());
        ps.setString(3, airport.getName());
        ps.setString(4, airport.getCity());
        ps.setString(5, airport.getCountry());
        ps.setString(6, airport.getTimezone());
    }

    // Update element
    @Override
    protected String buildUpdateSql() {
        return """
                UPDATE %s
                SET iata = ?, icao = ?, name = ?, city = ?, country = ?, timezone = ?
                WHERE %s = ?
                """.formatted(TABLE_NAME, ID_NAME);
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Airport airport) throws SQLException {
        ps.setString(1, airport.getIata());
        ps.setString(2, airport.getIcao());
        ps.setString(3, airport.getName());
        ps.setString(4, airport.getCity());
        ps.setString(5, airport.getCountry());
        ps.setString(6, airport.getTimezone());
        ps.setInt(7, airport.getId());
    }

    // Check duplicates (via service)
    @Override
    protected String buildExistsSql() {
        return """
                SELECT 1 FROM %s
                WHERE iata = ? AND icao = ? AND name = ? AND city = ? AND country = ? AND timezone = ?
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, Airport airport) throws SQLException {
        ps.setString(1, airport.getIata());
        ps.setString(2, airport.getIcao());
        ps.setString(3, airport.getName());
        ps.setString(4, airport.getCity());
        ps.setString(5, airport.getCountry());
        ps.setString(6, airport.getTimezone());
    }*/
}
