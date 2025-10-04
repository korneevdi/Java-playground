package airport.dao.basic;

import airport.entity.basic.Airline;
import airport.entity.basic.Airplane;
import airport.entity.contact.AirlineContact;
import airport.entity.dictionary.Type;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDao extends AbstractBasicDao<Airplane> {

    public AirplaneDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT ap.airplane_id,
                       ap.registration_number,
                       ap.model,
                       ap.total_capacity,
                       a.airline_id,
                       a.iata,
                       a.icao,
                       a.name,
                       ac.contact_id,
                       ac.contact_name,
                       ac.email,
                       ac.phone,
                       ac.headquarter_city,
                       ac.notes,
                       t.type_id,
                       t.type_name
                FROM airplanes ap
                JOIN airlines a ON ap.airline = a.airline_id
                JOIN types t ON ap.type = t.type_id
                JOIN airline_contacts ac ON a.contact = ac.contact_id
                """;
    }

    @Override
    protected Airplane mapRow(ResultSet resultSet) throws SQLException {
        AirlineContact contact = new AirlineContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("contact_name"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("headquarter_city"),
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
