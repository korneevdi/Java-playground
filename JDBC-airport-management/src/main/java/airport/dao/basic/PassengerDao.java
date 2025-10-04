package airport.dao.basic;

import airport.entity.basic.Passenger;
import airport.entity.dictionary.PersonSex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerDao extends AbstractBasicDao<Passenger> {

    private final static String TABLE_NAME = "passengers";
    private final static String ID_NAME = "passenger_id";

    public PassengerDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT passenger_id, first_name, last_name, sex_id, sex_name, age,
                       passport_country, passport_number
                FROM passengers
                JOIN person_sex ON passengers.sex = person_sex.sex_id
                """;
    }

    @Override
    protected Passenger mapRow(ResultSet resultSet) throws SQLException {
        PersonSex sex = new PersonSex(
                resultSet.getInt("sex_id"),
                resultSet.getString("sex_name")
        );
        return new Passenger(
                resultSet.getInt("passenger_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                sex,
                resultSet.getInt("age"),
                resultSet.getString("passport_country"),
                resultSet.getString("passport_number")
        );
    }
}
