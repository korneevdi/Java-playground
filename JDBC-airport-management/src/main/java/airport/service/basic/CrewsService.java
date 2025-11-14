package airport.service.basic;

import airport.dao.basic.CrewsDao;
import airport.dao.dictionary.SexesDao;
import airport.entity.basic.Crew;
import airport.entity.dictionary.Sex;
import airport.service.AbstractService;

import java.time.LocalDate;
import java.util.Map;

public class CrewsService extends AbstractService<Crew> {

    private final SexesDao sexesDao;
    private final static String ENTITY_NAME = "Flight Crew";

    public CrewsService(CrewsDao crewDao, SexesDao sexesDao) {
        super(crewDao, ENTITY_NAME);
        this.sexesDao = sexesDao;

        // Set the map of the fields and max lengths
        stringFields = Map.ofEntries(
                Map.entry("pilot_license_number", 20),
                Map.entry("first_name", 50),
                Map.entry("last_name", 50),
                Map.entry("sex_name", 20),
                Map.entry("passport_country", 20),
                Map.entry("passport_number", 20)
        );

        dateFields.add("birth_date");
    }

    // Add new crew
    public void add(String pilotLicenseNumber, String firstName, String lastName, String sexName,
                    String stringBirthDate, String passportCountry, String passportNumber) {

        // Check whether sex exists (using sex name)
        Sex sex = sexesDao.findByField("sex_name", sexName)
                .stream().findFirst().orElse(null);
        if(sex == null) {
            System.out.printf("Sex '%s' does not exist. Please, add new sex first", sexName);
            return;
        }

        // Parse the birth date to the correct data type
        LocalDate birthDate = parseDate(stringBirthDate);

        // Create Crew object and insert it
        Crew crew = new Crew(0, pilotLicenseNumber, firstName, lastName, sex,
                birthDate, passportCountry, passportNumber);

        addElement(crew);
    }

    // Delete element
    public void delete(String passportCountry, String passportNumber) {
        Map<String, String> map = Map.of(
                "passport_country", passportCountry,
                "passport_number", passportNumber);
        deleteElement(map);
    }
}
