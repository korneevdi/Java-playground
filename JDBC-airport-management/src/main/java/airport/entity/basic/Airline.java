package airport.entity.basic;

import airport.entity.contact.AirlineContact;

import java.util.Objects;

public class Airline {

    private int id;

    private String iata;

    private String icao;

    private String name;

    private AirlineContact contact;

    public Airline(int id, String iata, String icao, String name, AirlineContact contact) {
        this.id = id;
        this.iata = iata;
        this.icao = icao;
        this.name = name;
        this.contact = contact;
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

    public AirlineContact getContact() {
        return contact;
    }

    public void setContact(AirlineContact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return """
                Airline
                    ID: %s,
                    IATA: %s,
                    ICAO: %s,
                    name: %s,
                    contact name: %s,
                    contact email: %s,
                    contact phone: %s,
                    city: %s,
                    notes: %s
                """.formatted(id, iata, icao, name, contact.getContactName(), contact.getEmail(),
                contact.getPhone(), contact.getCity(), contact.getNotes());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airline airline = (Airline) o;
        return Objects.equals(iata, airline.iata) &&
                Objects.equals(icao, airline.icao) &&
                Objects.equals(name, airline.name) &&
                Objects.equals(contact, airline.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iata, icao, name, contact);
    }
}
