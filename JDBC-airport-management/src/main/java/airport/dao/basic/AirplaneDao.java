package airport.dao.basic;

import airport.entity.basic.Airline;
import airport.entity.basic.Airplane;
import airport.entity.contact.AirlineContact;
import airport.entity.dictionary.Type;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AirplaneDao extends AbstractBasicDao<Airplane> {

    private final static String TABLE_NAME = "airplanes";
    private final static String ID_NAME = "airplane_id";

    public AirplaneDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT ap.airplane_id, ap.registration_number, ap.model, ap.total_capacity, ap.type,
                       a.airline_id, a.iata, a.icao, a.name,
                       ac.contact_id, ac.contact_name, ac.contact_email, ac.contact_phone, ac.city, ac.notes,
                       t.type_id, t.type_name
                FROM airplanes ap
                JOIN airlines a ON ap.airline = a.airline_id
                JOIN types t ON ap.type = t.type_id
                JOIN airline_contacts ac ON a.contact = ac.contact_id
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT ap.airplane_id, ap.registration_number, ap.model, ap.total_capacity, ap.type,
                       a.airline_id, a.iata, a.icao, a.name,
                       ac.contact_id, ac.contact_name, ac.contact_email, ac.contact_phone, ac.city, ac.notes,
                       t.type_id, t.type_name
                FROM airplanes ap
                JOIN airlines a ON ap.airline = a.airline_id
                JOIN types t ON ap.type = t.type_id
                JOIN airline_contacts ac ON a.contact = ac.contact_id
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT ap.airplane_id, ap.registration_number, ap.model, ap.total_capacity, ap.type,
                       a.airline_id, a.iata, a.icao, a.name,
                       ac.contact_id, ac.contact_name, ac.contact_email, ac.contact_phone, ac.city, ac.notes,
                       t.type_id, t.type_name
                FROM airplanes ap
                JOIN airlines a ON ap.airline = a.airline_id
                JOIN types t ON ap.type = t.type_id
                JOIN airline_contacts ac ON a.contact = ac.contact_id
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected Airplane mapRow(ResultSet resultSet) throws SQLException {
        AirlineContact contact = new AirlineContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("contact_name"),
                resultSet.getString("contact_email"),
                resultSet.getString("contact_phone"),
                resultSet.getString("city"),
                resultSet.getString("notes")
        );

        Airline airline = new Airline(
                resultSet.getInt("airline_id"),
                resultSet.getString("iata"),
                resultSet.getString("icao"),
                resultSet.getString("name"),
                contact
        );

        Type type = new Type(
                resultSet.getInt("type_id"),
                resultSet.getString("type_name")
        );

        return new Airplane(
                resultSet.getInt("airplane_id"),
                airline,
                resultSet.getString("registration_number"),
                resultSet.getString("model"),
                resultSet.getInt("total_capacity"),
                type
        );
    }
}
