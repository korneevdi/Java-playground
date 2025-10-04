package airport.dao.basic;

import airport.entity.basic.AirportEmployee;
import airport.entity.contact.EmergencyContact;
import airport.entity.contact.EmployeeContact;
import airport.entity.dictionary.EmployeeRole;
import airport.entity.dictionary.PersonSex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AirportEmployeeDao extends AbstractBasicDao<AirportEmployee> {

    public AirportEmployeeDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT e.employee_id,
                       e.first_name,
                       e.last_name,
                       r.role_id,
                       r.role_name,
                       s.sex_id,
                       s.sex_name,
                       e.birth_date,
                       e.passport_country,
                       e.passport_number,
                       ec.contact_id    AS emp_contact_id,
                       ec.email         AS emp_email,
                       ec.phone         AS emp_phone,
                       ec.city          AS emp_city,
                       ec.address       AS emp_address,
                       ec.notes         AS emp_notes,
                       emc.contact_id   AS emerg_contact_id,
                       emc.contact_name AS emerg_name,
                       emc.relation     AS emerg_relation,
                       emc.phone        AS emerg_phone
                FROM airport_employees e
                JOIN airport_employee_roles r ON e.role = r.role_id
                JOIN person_sex s             ON e.sex = s.sex_id
                JOIN employee_contacts ec     ON e.contact = ec.contact_id
                JOIN emergency_contacts emc   ON e.emergency_contact = emc.contact_id;
                """;
    }

    @Override
    protected AirportEmployee mapRow(ResultSet rs) throws SQLException {
        EmployeeContact empContact = new EmployeeContact(
                rs.getInt("emp_contact_id"),
                rs.getString("emp_email"),
                rs.getString("emp_phone"),
                rs.getString("emp_city"),
                rs.getString("emp_address"),
                rs.getString("emp_notes")
        );

        EmergencyContact emergContact = new EmergencyContact(
                rs.getInt("emerg_contact_id"),
                rs.getString("emerg_name"),
                rs.getString("emerg_relation"),
                rs.getString("emerg_phone")
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
                rs.getInt("employee_id"),
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
