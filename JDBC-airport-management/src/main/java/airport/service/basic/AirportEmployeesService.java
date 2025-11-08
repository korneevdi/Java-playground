package airport.service.basic;

import airport.dao.basic.AirportEmployeesDao;
import airport.dao.contact.AirportEmployeeContactsDao;
import airport.dao.contact.EmergencyContactsDao;
import airport.dao.dictionary.AirportEmployeeRolesDao;
import airport.dao.dictionary.SexesDao;
import airport.entity.basic.AirportEmployee;
import airport.entity.contact.AirportEmployeeContact;
import airport.entity.contact.EmergencyContact;
import airport.entity.dictionary.AirportEmployeeRole;
import airport.entity.dictionary.Sex;
import airport.service.AbstractService;

import java.time.LocalDate;
import java.util.Map;

public class AirportEmployeesService extends AbstractService<AirportEmployee> {

    private final AirportEmployeeRolesDao airportEmployeeRolesDao;
    private final SexesDao sexesDao;
    private final AirportEmployeeContactsDao airportEmployeeContactsDao;
    private final EmergencyContactsDao emergencyContactsDao;
    private final static String ENTITY_NAME = "Airport Employee";

    public AirportEmployeesService(AirportEmployeesDao airportEmployeesDao,
                                   AirportEmployeeRolesDao airportEmployeeRolesDao,
                                   SexesDao sexesDao,
                                   AirportEmployeeContactsDao airportEmployeeContactsDao,
                                   EmergencyContactsDao emergencyContactsDao) {
        super(airportEmployeesDao, ENTITY_NAME);
        this.airportEmployeeRolesDao = airportEmployeeRolesDao;
        this.sexesDao = sexesDao;
        this.airportEmployeeContactsDao = airportEmployeeContactsDao;
        this.emergencyContactsDao = emergencyContactsDao;

        // Set the map of the fields and max lengths
        stringFields = Map.ofEntries(
                Map.entry("first_name", 50),
                Map.entry("last_name", 50),
                Map.entry("role_name", 100),
                Map.entry("sex_name", 20),
                Map.entry("passport_country", 20),
                Map.entry("passport_number", 20),
                Map.entry("emp_contact_email", 100),
                Map.entry("emp_contact_phone", 30),
                Map.entry("city", 25),
                Map.entry("address", 200),
                Map.entry("notes", 1000),
                Map.entry("emerg_contact_name", 100),
                Map.entry("emerg_contact_relation", 30),
                Map.entry("emerg_contact_phone", 30)
        );

        dateFields.add("birth_date");
    }

    // Add new airport employee
    public void add(String firstName, String lastName, String roleName, String sexName,
                    String stringBirthDate, String passportCountry, String passportNumber,
                    String contactEmail, String contactPhone, String city, String address, String notes,
                    String emergencyContactName, String emergencyContactRelation, String emergencyContactPhone ) {

        // Check whether airport employee role exists (using role name)
        AirportEmployeeRole role = airportEmployeeRolesDao.findByField("role_name", roleName)
                .stream().findFirst().orElse(null);
        if(role == null) {
            System.out.printf("Airport employee role '%s' does not exist. Please, add new role first", roleName);
            return;
        }

        // Check whether sex exists
        Sex sex = sexesDao.findByField("sex_name", sexName)
                .stream().findFirst().orElse(null);
        if(sex == null) {
            System.out.printf("Sex '%s' does not exist. Please, add new sex first", sexName);
            return;
        }

        AirportEmployeeContact contact = new AirportEmployeeContact(0, contactEmail, contactPhone, city, address, notes);

        // Make sure the contact already exists or create it
        if(!airportEmployeeContactsDao.exists(contact)) {
            airportEmployeeContactsDao.insert(contact);
        }

        // Get the ID of an existing or new contact
        int contactId = airportEmployeeContactsDao.findId(Map.of("contact_email", contactEmail))
                .orElseThrow(() -> new IllegalStateException("Could not retrieve contact ID after insert"));

        contact.setId(contactId);

        EmergencyContact emergencyContact = new EmergencyContact(0, emergencyContactName,
                emergencyContactRelation, emergencyContactPhone);

        if(!emergencyContactsDao.exists(emergencyContact)) {
            emergencyContactsDao.insert(emergencyContact);
        }

        int emergencyContactId = emergencyContactsDao.findId(Map.of("contact_phone", emergencyContactPhone))
                .orElseThrow(() -> new IllegalStateException("Could not retrieve emergency contact ID after insert"));

        emergencyContact.setId(emergencyContactId);

        // Parse the birth date to the correct data type
        LocalDate birthDate = parseDate(stringBirthDate);

        // Create AirportEmployee object and insert it
        AirportEmployee airportEmployee = new AirportEmployee(0, firstName, lastName, role, sex,
                birthDate, passportCountry, passportNumber, contact, emergencyContact);

        addElement(airportEmployee);
    }
}
