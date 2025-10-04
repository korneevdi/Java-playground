package airport.entity.basic;

import java.util.Objects;

public class Airport {

    private int id;

    private String iata;

    private String icao;

    private String name;

    private String city;

    private String country;

    private String timezone;

    public Airport(int id, String iata, String icao, String name, String city, String country, String timezone) {
        this.id = id;
        this.iata = iata;
        this.icao = icao;
        this.name = name;
        this.city = city;
        this.country = country;
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return """
                Airport
                    ID: %s,
                    IATA: %s,
                    ICAO: %s,
                    name: %s,
                    city: %s,
                    country: %s,
                    timezone: %s
                """.formatted(id, iata, icao, name, city, country, timezone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(iata, airport.iata) &&
                Objects.equals(icao, airport.icao) &&
                Objects.equals(name, airport.name) &&
                Objects.equals(city, airport.city) &&
                Objects.equals(country, airport.country) &&
                Objects.equals(timezone, airport.timezone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iata, icao, name, city, country, timezone);
    }
}
