package airport.service.basic;

import airport.dao.basic.AirportDao;
import airport.entity.basic.Airport;

import java.util.List;
import java.util.Optional;

public class AirportService extends AbstractBasicService<Airport> {

    private final AirportDao airportDao;

    public AirportService(AirportDao dao) {
        super(dao);
        this.airportDao = dao;

        // Set the map of the fields and max lengths
        fieldMaxLengths.put("iata", 3);
        fieldMaxLengths.put("icao", 4);
        fieldMaxLengths.put("name", 100);
        fieldMaxLengths.put("city", 25);
        fieldMaxLengths.put("country", 25);
        fieldMaxLengths.put("timezone", 40);
    }

    // Airport with certain IATA
    public void findElementByIata(String iata) {
        Optional<Airport> airport = airportDao.findByIata(iata);
        if (airport.isPresent()) {
            System.out.println("Airport found with IATA '" + iata + "':");
            System.out.println(airport.get());
        } else {
            System.out.println("No data found");
        }
    }

    // Airport with certain ICAO
    public void findElementByIcao(String icao) {
        Optional<Airport> airport = airportDao.findByIcao(icao);
        if (airport.isPresent()) {
            System.out.println("Airport found with ICAO '" + icao + "':");
            System.out.println(airport.get());
        } else {
            System.out.println("No data found");
        }
    }

    // Airport with certain name
    public void findElementByName(String name) {
        Optional<Airport> airport = airportDao.findByName(name);
        if (airport.isPresent()) {
            System.out.println("Airport found with name '" + name + "':");
            System.out.println(airport.get());
        } else {
            System.out.println("No data found");
        }
    }

    // All airports from certain city
    public void findAllElementsByCity(String city) {
        List<Airport> allElements = airportDao.findAllByCity(city);
        if (!allElements.isEmpty()) {
            System.out.println("All airports found in city '" + city + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // All airports from certain city
    public void findAllElementsByCountry(String country) {
        List<Airport> allElements = airportDao.findAllByCountry(country);
        if (!allElements.isEmpty()) {
            System.out.println("All airports found in country '" + country + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // All airports from certain city
    public void findAllElementsByTimezone(String timezone) {
        List<Airport> allElements = airportDao.findAllByTimezone(timezone);
        if (!allElements.isEmpty()) {
            System.out.println("All airports found in timezone '" + timezone + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

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

        updateContact(oldAirport, newAirport);
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
    }
}
