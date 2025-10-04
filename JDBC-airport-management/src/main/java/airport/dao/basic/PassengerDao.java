package airport.dao.basic;

import airport.entity.basic.Passenger;
import airport.entity.dictionary.PersonSex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassengerDao extends AbstractBasicDao<Passenger> {

    public PassengerDao(Connection connection) {
        super(connection);
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
