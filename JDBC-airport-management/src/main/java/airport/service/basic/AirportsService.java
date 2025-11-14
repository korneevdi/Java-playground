package airport.service.basic;

import airport.dao.basic.AirportsDao;
import airport.entity.basic.Airport;
import airport.service.AbstractService;

import java.util.Map;

public class AirportsService extends AbstractService<Airport> {

    private final static String ENTITY_NAME = "Airport";

    public AirportsService(AirportsDao airportDao) {
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

    // Add new airport
    public void add(String iata, String icao, String name, String city, String country, String timezone) {
        addElement(new Airport(0, iata, icao, name, city, country, timezone));
    }

    // Delete element
    public void delete(String iata) {
        Map<String, String> map = Map.of("iata", iata);
        deleteElement(map);
    }

    /*
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
    */
}
