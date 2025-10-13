package airport.dao.basic;

import airport.entity.basic.Passenger;
import airport.entity.dictionary.Sex;

import java.sql.*;

public class PassengersDao extends AbstractBasicDao<Passenger> {

    private final static String TABLE_NAME = "passengers";
    private final static String ID_NAME = "passenger_id";

    public PassengersDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT p.passenger_id, p.first_name, p.last_name, p.age, p.passport_country, p.passport_number,
                       s.sex_id, s.sex_name
                FROM passengers p
                JOIN sexes s ON p.sex = s.sex_id
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT p.passenger_id, p.first_name, p.last_name, p.age, p.passport_country, p.passport_number,
                       s.sex_id, s.sex_name
                FROM passengers p
                JOIN sexes s ON p.sex = s.sex_id
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT p.passenger_id, p.first_name, p.last_name, p.age, p.passport_country, p.passport_number,
                       s.sex_id, s.sex_name
                FROM passengers p
                JOIN sexes s ON p.sex = s.sex_id
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected Passenger mapRow(ResultSet resultSet) throws SQLException {
        Sex sex = new Sex(
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
