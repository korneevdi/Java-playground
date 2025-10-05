package airport.dao.basic;

import airport.entity.basic.Crew;
import airport.entity.dictionary.PersonSex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CrewDao extends AbstractBasicDao<Crew> {

    private final static String TABLE_NAME = "flight_crews";
    private final static String ID_NAME = "flight_crew_id";

    public CrewDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT fc.flight_crew_id, fc.pilot_license_number, fc.first_name, fc.last_name, fc.birth_date, fc.passport_country, fc.passport_number,
                       s.sex_id, s.sex_name
                FROM flight_crews fc
                JOIN sexes s ON fc.sex = s.sex_id
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT fc.flight_crew_id, fc.pilot_license_number, fc.first_name, fc.last_name, fc.birth_date, fc.passport_country, fc.passport_number,
                       s.sex_id, s.sex_name
                FROM flight_crews fc
                JOIN sexes s ON fc.sex = s.sex_id
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected Crew mapRow(ResultSet resultSet) throws SQLException {
        PersonSex sex = new PersonSex(
                resultSet.getInt("sex_id"),
                resultSet.getString("sex_name")
        );
        return new Crew(
                resultSet.getInt("flight_crew_id"),
                resultSet.getString("pilot_license_number"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                sex,
                resultSet.getObject("birth_date", LocalDate.class),
                resultSet.getString("passport_country"),
                resultSet.getString("passport_number")
        );
    }
}
