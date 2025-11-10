package airport.dao.basic;

import airport.dao.AbstractDao;
import airport.entity.basic.Airport;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class AirportsDao extends AbstractDao<Airport> {

    private final static String TABLE_NAME = "airports";
    private final static String ID_NAME = "airport_id";
    private final static List<String> ALL_FIELDS = List.of(
            "iata",
            "icao",
            "name",
            "city",
            "country",
            "timezone"
    );
    private final static List<String> UNIQUE_FIELDS = List.of(
            "iata",
            "icao",
            "name"
    );

    public AirportsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, ALL_FIELDS, UNIQUE_FIELDS);
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
    protected void setExistsStatement(PreparedStatement ps, Airport airport) throws SQLException {
        ps.setString(1, airport.getIata());
        ps.setString(2, airport.getIcao());
        ps.setString(3, airport.getName());
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

    @Override
    public Map<String, String> getColumnToFieldMap() {
        return Map.ofEntries(
                Map.entry("airport_id", "id"),
                Map.entry("iata", "iata"),
                Map.entry("icao", "icao"),
                Map.entry("name", "name"),
                Map.entry("city", "city"),
                Map.entry("country", "country"),
                Map.entry("timezone", "timezone")
        );
    }
}
