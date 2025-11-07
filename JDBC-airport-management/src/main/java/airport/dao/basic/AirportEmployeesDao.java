package airport.dao.basic;

import airport.dao.AbstractDao;
import airport.entity.basic.AirportEmployee;
import airport.entity.contact.AirportEmployeeContact;
import airport.entity.contact.EmergencyContact;
import airport.entity.dictionary.AirportEmployeeRole;
import airport.entity.dictionary.Sex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AirportEmployeesDao extends AbstractDao<AirportEmployee> {

    private final static String TABLE_NAME = "airport_employees";
    private final static String ID_NAME = "employee_id";
    private final static List<String> ALL_FIELDS = List.of(
            "first_name",
            "last_name",
            "role",
            "sex",
            "birth_date",
            "passport_country",
            "passport_number",
            "contact",
            "emergency_contact"
    );
    private final static List<String> UNIQUE_FIELDS = List.of(
            "passport_country",
            "passport_number"
    );

    public AirportEmployeesDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, ALL_FIELDS, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql() {
        return """
                SELECT ae.airport_employee_id, ae.first_name, ae.last_name, ae.birth_date, ae.passport_country, ae.passport_number,
                       aer.role_id, aer.role_name,
                       s.sex_id, s.sex_name,
                       aec.contact_id AS emp_contact_id, aec.contact_email AS emp_contact_email, aec.contact_phone AS emp_contact_phone, aec.city, aec.address, aec.notes,
                       ec.contact_id AS emerg_contact_id, ec.contact_name AS emerg_contact_name, ec.contact_relation AS emerg_contact_relation, ec.contact_phone AS emerg_contact_phone
                FROM airport_employees ae
                JOIN airport_employee_roles aer ON ae.role = aer.role_id
                JOIN sexes s ON ae.sex = s.sex_id
                JOIN airport_employee_contacts aec ON ae.contact = aec.contact_id
                JOIN emergency_contacts ec ON ae.emergency_contact = ec.contact_id
                """;
    }

    @Override
    protected String buildFindByIdSql() {
        return """
                SELECT ae.airport_employee_id, ae.first_name, ae.last_name, ae.birth_date, ae.passport_country, ae.passport_number,
                       aer.role_id, aer.role_name,
                       s.sex_id, s.sex_name,
                       aec.contact_id AS emp_contact_id, aec.contact_email AS emp_contact_email, aec.contact_phone AS emp_contact_phone, aec.city, aec.address, aec.notes,
                       ec.contact_id AS emerg_contact_id, ec.contact_name AS emerg_contact_name, ec.contact_relation AS emerg_contact_relation, ec.contact_phone AS emerg_contact_phone
                FROM airport_employees ae
                JOIN airport_employee_roles aer ON ae.role = aer.role_id
                JOIN sexes s ON ae.sex = s.sex_id
                JOIN airport_employee_contacts aec ON ae.contact = aec.contact_id
                JOIN emergency_contacts ec ON ae.emergency_contact = ec.contact_id
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        String qualifiedFieldName = generateQualifiedFieldName(fieldName);

        return """
            SELECT ae.airport_employee_id, ae.first_name, ae.last_name, ae.birth_date, ae.passport_country, ae.passport_number,
                   aer.role_id, aer.role_name,
                   s.sex_id, s.sex_name,
                   aec.contact_id AS emp_contact_id, aec.contact_email AS emp_contact_email, aec.contact_phone AS emp_contact_phone, aec.city, aec.address, aec.notes,
                   ec.contact_id AS emerg_contact_id, ec.contact_name AS emerg_contact_name, ec.contact_relation AS emerg_contact_relation, ec.contact_phone AS emerg_contact_phone
            FROM airport_employees ae
            JOIN airport_employee_roles aer ON ae.role = aer.role_id
            JOIN sexes s ON ae.sex = s.sex_id
            JOIN airport_employee_contacts aec ON ae.contact = aec.contact_id
            JOIN emergency_contacts ec ON ae.emergency_contact = ec.contact_id
            WHERE %s = ?
            """.formatted(qualifiedFieldName);
    }

    private String generateQualifiedFieldName(String fieldName) {
        String qualifiedFieldName;

        // Substitute an alias depending on which table the field belongs to
        if (fieldName.startsWith("emp_")) {
            qualifiedFieldName = "aec." + fieldName.replace("emp_", "");
        } else if (fieldName.startsWith("emerg_")) {
            qualifiedFieldName = "ec." + fieldName.replace("emerg_", "");
        } else if (List.of("first_name", "last_name", "birth_date", "passport_country", "passport_number").contains(fieldName)) {
            qualifiedFieldName = "ae." + fieldName;
        } else {
            qualifiedFieldName = fieldName; // fallback
        }

        return qualifiedFieldName;
    }

    @Override
    protected String buildExistsSql() {
        String whereClause = UNIQUE_FIELDS.stream()
                .map(f -> f + " = ?")
                .collect(Collectors.joining(" AND "));

        return "SELECT 1 FROM " + TABLE_NAME + " WHERE " + whereClause;
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, AirportEmployee airportEmployee) throws SQLException {
        ps.setString(1, airportEmployee.getPassportCountry());
        ps.setString(2, airportEmployee.getPassportNumber());
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, AirportEmployee airportEmployee) throws SQLException {
        ps.setString(1, airportEmployee.getFirstName());
        ps.setString(2, airportEmployee.getLastName());
        ps.setInt(3, airportEmployee.getRole().getId());
        ps.setInt(4, airportEmployee.getSex().getId());
        ps.setDate(5, java.sql.Date.valueOf(airportEmployee.getBirthDate()));
        ps.setString(6, airportEmployee.getPassportCountry());
        ps.setString(7, airportEmployee.getPassportNumber());
        ps.setInt(8, airportEmployee.getContact().getId());
        ps.setInt(9, airportEmployee.getEmergencyContact().getId());
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, AirportEmployee airportEmployee) throws SQLException {
        ps.setString(1, airportEmployee.getFirstName());
        ps.setString(2, airportEmployee.getLastName());
        ps.setInt(3, airportEmployee.getRole().getId());
        ps.setInt(4, airportEmployee.getSex().getId());
        ps.setDate(5, java.sql.Date.valueOf(airportEmployee.getBirthDate()));
        ps.setString(6, airportEmployee.getPassportCountry());
        ps.setString(7, airportEmployee.getPassportNumber());
        ps.setInt(8, airportEmployee.getContact().getId());
        ps.setInt(9, airportEmployee.getEmergencyContact().getId());
        ps.setInt(10, airportEmployee.getId());
    }

    @Override
    protected AirportEmployee mapRow(ResultSet rs) throws SQLException {
        AirportEmployeeContact empContact = new AirportEmployeeContact(
                rs.getInt("emp_contact_id"),
                rs.getString("emp_contact_email"),
                rs.getString("emp_contact_phone"),
                rs.getString("city"),
                rs.getString("address"),
                rs.getString("notes")
        );

        EmergencyContact emergContact = new EmergencyContact(
                rs.getInt("emerg_contact_id"),
                rs.getString("emerg_contact_name"),
                rs.getString("emerg_contact_relation"),
                rs.getString("emerg_contact_phone")
        );

        AirportEmployeeRole role = new AirportEmployeeRole(
                rs.getInt("role_id"),
                rs.getString("role_name")
        );

        Sex sex = new Sex(
                rs.getInt("sex_id"),
                rs.getString("sex_name")
        );

        return new AirportEmployee(
                rs.getInt("airport_employee_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                role,
                sex,
                rs.getObject("birth_date", LocalDate.class),
                rs.getString("passport_country"),
                rs.getString("passport_number"),
                empContact,
                emergContact
        );
    }
}
