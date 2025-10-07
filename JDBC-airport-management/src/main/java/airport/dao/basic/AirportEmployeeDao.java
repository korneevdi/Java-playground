package airport.dao.basic;

import airport.entity.basic.AirportEmployee;
import airport.entity.contact.EmergencyContact;
import airport.entity.contact.EmployeeContact;
import airport.entity.dictionary.EmployeeRole;
import airport.entity.dictionary.PersonSex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AirportEmployeeDao extends AbstractBasicDao<AirportEmployee> {

    private final static String TABLE_NAME = "airport_employees";
    private final static String ID_NAME = "employee_id";

    public AirportEmployeeDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
    }

    @Override
    protected String buildFindAllSql() {
        return """
                SELECT ae.airport_employee_id, ae.first_name, ae.last_name, ae.birth_date, ae.passport_country, ae.passport_number,
                       aer.role_id, aer.role_name,
                       s.sex_id, s.sex_name,
                       aec.contact_id AS emp_contact_id, aec.contact_email AS emp_contact_email, aec.contact_phone AS emp_contact_phone, aec.city, aec.address, aec.notes,
                       ec.contact_id AS emerg_contact_id, ec.contact_name AS emerg_contact_name, ec.relation AS emerg_contact_relation, ec.contact_phone AS emerg_contact_phone
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
                       ec.contact_id AS emerg_contact_id, ec.contact_name AS emerg_contact_name, ec.relation AS emerg_contact_relation, ec.contact_phone AS emerg_contact_phone
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
        return """
                SELECT ae.airport_employee_id, ae.first_name, ae.last_name, ae.birth_date, ae.passport_country, ae.passport_number,
                       aer.role_id, aer.role_name,
                       s.sex_id, s.sex_name,
                       aec.contact_id AS emp_contact_id, aec.contact_email AS emp_contact_email, aec.contact_phone AS emp_contact_phone, aec.city, aec.address, aec.notes,
                       ec.contact_id AS emerg_contact_id, ec.contact_name AS emerg_contact_name, ec.relation AS emerg_contact_relation, ec.contact_phone AS emerg_contact_phone
                FROM airport_employees ae
                JOIN airport_employee_roles aer ON ae.role = aer.role_id
                JOIN sexes s ON ae.sex = s.sex_id
                JOIN airport_employee_contacts aec ON ae.contact = aec.contact_id
                JOIN emergency_contacts ec ON ae.emergency_contact = ec.contact_id
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected AirportEmployee mapRow(ResultSet rs) throws SQLException {
        EmployeeContact empContact = new EmployeeContact(
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

        EmployeeRole role = new EmployeeRole(
                rs.getInt("role_id"),
                rs.getString("role_name")
        );

        PersonSex sex = new PersonSex(
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
