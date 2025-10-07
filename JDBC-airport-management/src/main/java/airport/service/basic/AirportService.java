package airport.service.basic;

import airport.dao.basic.AirportDao;
import airport.entity.basic.Airport;

import java.util.Map;

public class AirportService extends AbstractBasicService<Airport> {

    private final static String ENTITY_NAME = "Airport";

    public AirportService(AirportDao airportDao) {
        super(airportDao, ENTITY_NAME);

        // Set the map of the fields and max lengths
        stringFields = Map.of(
                "iata", 3,
                "icao", 4,
                "name", 100,
                "city", 25,
                "country", 25,
                "timezone", 40
        );
    }

    /*
    // Add new airport
    public void add(String iata, String icao, String name, String city, String country, String timezone) {
        Airport airport = new Airport(0, iata, icao, name, city, country, timezone);
        addContact(airport);
    }

    // Update airport
    public void update(String oldIata, String oldIcao, String oldName, String oldCity, String oldCountry, String oldTimezone,
                       String newIata, String newIcao, String newName, String newCity, String newCountry, String newTimezone) {

        Airport oldAirport = new Airport(0, oldIata, oldIcao, oldName, oldCity, oldCountry, oldTimezone);

        int id = airportDao.findByIata(oldIata).
                orElseThrow(() -> new RuntimeException("Contact to update is not found"))
                .getId();

        Airport newAirport = new Airport(id, newIata, newIcao, newName, newCity, newCountry, newTimezone);

        updateElement(oldAirport, newAirport);
    }

    // Delete airport
    public void delete(String iata, String icao, String name, String city, String country, String timezone) {
        Airport airport = new Airport(0, iata, icao, name, city, country, timezone);
        deleteContact(airport);
    }

    @Override
    protected boolean isValidContact(Airport airport) {
        return validateField("iata", airport.getIata()) &&
                validateField("icao", airport.getIcao()) &&
                validateField("name", airport.getName()) &&
                validateField("city", airport.getCity()) &&
                validateField("country", airport.getCountry()) &&
                validateField("timezone", airport.getTimezone());
    }

    @Override
    protected void existsOrNotOutput(Airport airport, boolean isExists) {
        String output =
                """
                Element with the following properties

                iata: %s,
                icao: %s,
                name: %s,
                city: %s,
                country: %s,
                timezone: %s
                """.formatted(airport.getIata(), airport.getIcao(), airport.getName(),
                        airport.getCity(), airport.getCountry(), airport.getTimezone());

        if(isExists) {
            output = output + "\nalready exists.";
        } else {
            output = output + "\ndoes not exist.";
        }
        System.out.println(output);
    }

    @Override
    protected boolean areContactsIdentical(Airport oldAirport, Airport newAirport) {
        return oldAirport.equals(newAirport);
    }

    @Override
    protected Optional<Integer> findId(Airport airport) {
        return airportDao.findByIata(airport.getIata()).map(Airport::getId);
    }*/
}
