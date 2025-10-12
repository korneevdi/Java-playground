package airport.entity.basic;

import airport.entity.dictionary.Sex;

import java.util.Objects;

public class Passenger {

    private int id;
    private String firstName;
    private String lastName;
    private Sex sex;
    private int age;
    private String passportCountry;
    private String passportNumber;

    public Passenger(int id, String firstName, String lastName, Sex sex, int age,
                     String passportCountry, String passportNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
        this.passportCountry = passportCountry;
        this.passportNumber = passportNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassportCountry() {
        return passportCountry;
    }

    public void setPassportCountry(String passportCountry) {
        this.passportCountry = passportCountry;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return """
                Passenger
                    ID: %s,
                    first name: %s,
                    last name: %s,
                    sex: %s,
                    age: %s,
                    passport country: %s,
                    passport number: %s
                """.formatted(id, firstName, lastName, sex.getName(), age, passportCountry, passportNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return age == passenger.age
                && Objects.equals(firstName, passenger.firstName)
                && Objects.equals(lastName, passenger.lastName)
                && Objects.equals(sex, passenger.sex)
                && Objects.equals(passportCountry, passenger.passportCountry)
                && Objects.equals(passportNumber, passenger.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, sex, age, passportCountry, passportNumber);
    }
}
