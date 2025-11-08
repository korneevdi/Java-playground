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

        // Create Airport object and insert it
        Airport airline = new Airport(0, iata, icao, name, city, country, timezone);

        addElement(airline);
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

    // Delete airport
    public void delete(String iata, String icao, String name, String city, String country, String timezone) {
        Airport airport = new Airport(0, iata, icao, name, city, country, timezone);
        deleteContact(airport);
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
    */
}
