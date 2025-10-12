package airport.entity.basic;

import airport.entity.dictionary.Type;

import java.util.Objects;

public class Airplane {

    private int id;
    private Airline airline;
    private String registrationNumber;
    private String model;
    private int capacity;
    private Type type;

    public Airplane(int id, Airline airline, String registrationNumber, String model, int capacity, Type type) {
        this.id = id;
        this.airline = airline;
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.capacity = capacity;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return """
                Airplane
                    ID: %s,
                    airline: %s,
                    registration number: %s,
                    model: %s,
                    total capacity: %s,
                    type: %s
                """.formatted(id, airline.getName(), registrationNumber, model, capacity, type.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return capacity == airplane.capacity
                && Objects.equals(airline.getName(), airplane.airline.getName())
                && Objects.equals(registrationNumber, airplane.registrationNumber)
                && Objects.equals(model, airplane.model)
                && Objects.equals(type, airplane.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airline.getName(), registrationNumber, model, capacity, type);
    }
}
