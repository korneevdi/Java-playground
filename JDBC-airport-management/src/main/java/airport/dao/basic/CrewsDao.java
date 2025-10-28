package airport.dao.basic;

import airport.entity.basic.Crew;
import airport.entity.dictionary.Sex;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CrewsDao extends AbstractBasicDao<Crew> {

    private final static String TABLE_NAME = "flight_crews";
    private final static String ID_NAME = "flight_crew_id";
    private final static List<String> UNIQUE_FIELDS = new ArrayList<>() {{
        add("passport_country");
        add("passport_number");
    }};

    public CrewsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
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
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT fc.flight_crew_id, fc.pilot_license_number, fc.first_name, fc.last_name, fc.birth_date, fc.passport_country, fc.passport_number,
                       s.sex_id, s.sex_name
                FROM flight_crews fc
                JOIN sexes s ON fc.sex = s.sex_id
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected String buildExistsSql() {
        return """
                SELECT 1 FROM %s
                WHERE %s = ? AND %s = ?
                """.formatted(TABLE_NAME, UNIQUE_FIELDS.get(0), UNIQUE_FIELDS.get(1));
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, Crew crew) throws SQLException {
        ps.setString(1, crew.getPassportCountry());
        ps.setString(2, crew.getPassportNumber());
    }

    @Override
    protected String buildInsertSql() {
        return """
        INSERT INTO flight_crews (pilot_license_number, first_name, last_name, sex, birth_date,
        passport_country, passport_number)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Crew crew) throws SQLException {
        ps.setString(1, crew.getPilotLicenseNumber());
        ps.setString(2, crew.getFirstName());
        ps.setString(3, crew.getLastName());
        ps.setInt(4, crew.getSex().getId());
        ps.setDate(5, java.sql.Date.valueOf(crew.getBirthDate()));
        ps.setString(6, crew.getPassportCountry());
        ps.setString(7, crew.getPassportNumber());
    }

    @Override
    protected Crew mapRow(ResultSet resultSet) throws SQLException {
        Sex sex = new Sex(
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
