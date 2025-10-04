package airport.dao.basic;

import airport.entity.basic.Airline;
import airport.entity.contact.AirlineContact;

import java.sql.*;

public class AirlineDao extends AbstractBasicDao<Airline> {

    private final static String TABLE_NAME = "airlines";
    private final static String ID_NAME = "airline_id";

    public AirlineDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT airline_id, iata, icao, name, contact_id,  contact_name, email, phone, headquarter_city, notes
                FROM airlines
                JOIN airline_contacts ON airlines.airline_id = airline_contacts.contact_id
                """;
    }

    @Override
    protected Airline mapRow(ResultSet resultSet) throws SQLException {
        AirlineContact contact = new AirlineContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("contact_name"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("headquarter_city"),
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




    /*private final static String TABLE_NAME = "airlines";

    private final static String SELECTED_FIELDS = "airline_id, iata, icao, name, contact_name, email, phone, headquarter_city, notes";

    private final static String ID_NAME = "airline_id";

    public AirlineDao(Connection connection) {
        super(connection, TABLE_NAME, SELECTED_FIELDS, ID_NAME);
    }

    public Optional<Airline> findByIata(String iata) {
        return findByField("iata", iata).stream().findFirst();
    }

    public Optional<Airline> findByIcao(String icao) {
        return findByField("icao", icao).stream().findFirst();
    }

    public Optional<Airline> findByName(String name) {
        return findByField("name", name).stream().findFirst();
    }

    public List<Airline> findAllByContactName(String contactName) {
        return findByField("contact_name", contactName);
    }

    public Optional<Airline> findByEmail(String email) {
        return findByField("email", email).stream().findFirst();
    }

    public List<Airline> findAllByPhone(String phone) {
        return findByField("phone", phone);
    }

    public List<Airline> findAllByCity(String city) {
        return findByField("headquarter_city", city);
    }

    // Show the list of elements
    @Override
    protected String buildFindAllSql() {
        return """
                SELECT %s FROM %s
                JOIN airline_contacts ON airlines.contact = airline_contacts.contact_id
                """.formatted(SELECTED_FIELDS, TABLE_NAME);
    }

    // Find element by id
    @Override
    protected String buildFindByIdSql() {
        return """
                SELECT %s FROM %s
                JOIN airline_contacts ON airlines.contact = airline_contacts.contact_id
                WHERE %s = ?
                """.formatted(SELECTED_FIELDS, TABLE_NAME, ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT %s FROM %s
                JOIN airline_contacts ON airlines.contact = airline_contacts.contact_id
                WHERE %s = ?
                """.formatted(SELECTED_FIELDS, TABLE_NAME, fieldName);
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

    // Check duplicates (via service)
    @Override
    protected String buildExistsSql() {
        return """
                SELECT 1 FROM %s
                WHERE iata = ? AND icao = ? AND name = ? AND contact = ?
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, Airline airline) throws SQLException {
        ps.setString(1, airline.getIata());
        ps.setString(2, airline.getIcao());
        ps.setString(3, airline.getName());
        ps.setInt(4, airline.getContact().getId());
    }*/
}
