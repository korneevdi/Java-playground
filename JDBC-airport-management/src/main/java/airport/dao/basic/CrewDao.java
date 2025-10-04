package airport.dao.basic;

import airport.entity.basic.Crew;
import airport.entity.dictionary.PersonSex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CrewDao extends AbstractBasicDao<Crew> {

    private final static String TABLE_NAME = "crews";
    private final static String ID_NAME = "employee_id";

    public CrewDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT employee_id, pilot_license_number, first_name, last_name, sex_id, sex_name,
                       birth_date, passport_country, passport_number
                FROM %s
                JOIN person_sex ON crews.sex = person_sex.sex_id
                """.formatted(TABLE_NAME);
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT employee_id, pilot_license_number, first_name, last_name, sex_id, sex_name,
                       birth_date, passport_country, passport_number
                FROM %s
                JOIN person_sex ON crews.sex = person_sex.sex_id
                WHERE %s = ?
                """.formatted(TABLE_NAME, ID_NAME);
    }

    @Override
    protected Crew mapRow(ResultSet resultSet) throws SQLException {
        PersonSex sex = new PersonSex(
                resultSet.getInt("sex_id"),
                resultSet.getString("sex_name")
        );
        return new Crew(
                resultSet.getInt("employee_id"),
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
