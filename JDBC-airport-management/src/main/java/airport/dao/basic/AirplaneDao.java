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
                SELECT *
                FROM %s
                JOIN airlines ON airplanes.airline = airlines.airline_id
                JOIN types ON airplanes.type = types.type_id
                JOIN airline_contacts ON airlines.contact = airline_contacts.contact_id
                """.formatted(TABLE_NAME);
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT *
                FROM %s
                JOIN airlines ON airplanes.airline = airlines.airline_id
                JOIN types ON airplanes.type = types.type_id
                JOIN airline_contacts ON airlines.contact = airline_contacts.contact_id
                WHERE %s = ?
                """.formatted(TABLE_NAME, ID_NAME);
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
