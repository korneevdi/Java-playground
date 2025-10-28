package airport.dao.basic;

import airport.entity.basic.Airline;
import airport.entity.contact.AirlineContact;

import java.sql.*;
import java.util.List;

public class AirlinesDao extends AbstractBasicDao<Airline> {

    private final static String TABLE_NAME = "airlines";
    private final static String ID_NAME = "airline_id";
    private final static List<String> UNIQUE_FIELDS = List.of(
            "iata",
            "icao",
            "name"
    );

    public AirlinesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql() {
        return """
                SELECT a.airline_id, a.iata, a.icao, a.name,
                       ac.contact_id, ac.contact_name, ac.contact_email, ac.contact_phone, ac.city, ac.notes
                FROM airlines a
                JOIN airline_contacts ac ON a.airline_id = ac.contact_id
                """;
    }

    @Override
    protected String buildFindByIdSql() {
        return """
                SELECT a.airline_id, a.iata, a.icao, a.name,
                       ac.contact_id, ac.contact_name, ac.contact_email, ac.contact_phone, ac.city, ac.notes
                FROM airlines a
                JOIN airline_contacts ac ON a.airline_id = ac.contact_id
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT a.airline_id, a.iata, a.icao, a.name,
                       ac.contact_id, ac.contact_name, ac.contact_email, ac.contact_phone, ac.city, ac.notes
                FROM airlines a
                JOIN airline_contacts ac ON a.airline_id = ac.contact_id
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, Airline airline) throws SQLException {
        ps.setString(1, airline.getIata());
    }

    @Override
    protected String buildInsertSql() {
        return """
        INSERT INTO airlines (iata, icao, name, contact)
        VALUES (?, ?, ?, ?)
        """;
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Airline airline) throws SQLException {
        ps.setString(1, airline.getIata());
        ps.setString(2, airline.getIcao());
        ps.setString(3, airline.getName());
        ps.setInt(4, airline.getContact().getId());
    }

    @Override
    protected Airline mapRow(ResultSet resultSet) throws SQLException {
        AirlineContact contact = new AirlineContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("contact_name"),
                resultSet.getString("contact_email"),
                resultSet.getString("contact_phone"),
                resultSet.getString("city"),
                resultSet.getString("notes")
        );
        return new Airline(
                resultSet.getInt("airline_id"),
                resultSet.getString("iata"),
                resultSet.getString("icao"),
                resultSet.getString("name"),
                contact
        );
    }



    /*
    // Update element
    @Override
    protected String buildUpdateSql() {
        return """
                UPDATE %s
                SET iata = ?, icao = ?, name = ?, contact = ?
                WHERE %s = ?
                """.formatted(TABLE_NAME, ID_NAME);
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Airline airline) throws SQLException {
        ps.setString(1, airline.getIata());
        ps.setString(2, airline.getIcao());
        ps.setString(3, airline.getName());
        ps.setInt(4, airline.getContact().getId());
        ps.setInt(5, airline.getId());
    }
    */
}
