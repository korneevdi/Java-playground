package airport.dao.basic;

import airport.entity.basic.Airport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AirportDao extends AbstractBasicDao<Airport> {

    private final static String TABLE_NAME = "airports";

    private final static String SELECTED_FIELDS = "airport_id, iata, icao, name, city, country, timezone";

    private final static String ID_NAME = "airport_id";

    public AirportDao(Connection connection) {
        super(connection, TABLE_NAME, SELECTED_FIELDS, ID_NAME);
    }

    public Optional<Airport> findByIata(String iata) {
        return findByField("iata", iata).stream().findFirst();
    }

    public Optional<Airport> findByIcao(String icao) {
        return findByField("icao", icao).stream().findFirst();
    }

    public Optional<Airport> findByName(String name) {
        return findByField("name", name).stream().findFirst();
    }

    public List<Airport> findAllByCity(String city) {
        return findByField("city", city);
    }

    public List<Airport> findAllByCountry(String country) {
        return findByField("country", country);
    }

    public List<Airport> findAllByTimezone(String timezone) {
        return findByField("timezone", timezone);
    }

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
}
