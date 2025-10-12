package airport.entity.basic;

import airport.entity.dictionary.Sex;

import java.time.LocalDate;
import java.util.Objects;

public class Crew {

    private int id;
    private String pilotLicenseNumber;
    private String firstName;
    private String lastName;
    private Sex sex;
    private LocalDate birthDate;
    private String passportCountry;
    private String passportNumber;

    public Crew(int id, String pilotLicenseNumber, String firstName, String lastName, Sex sex,
                LocalDate birthDate, String passportCountry, String passportNumber) {
        this.id = id;
        this.pilotLicenseNumber = pilotLicenseNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.passportCountry = passportCountry;
        this.passportNumber = passportNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPilotLicenseNumber() {
        return pilotLicenseNumber;
    }

    public void setPilotLicenseNumber(String pilotLicenseNumber) {
        this.pilotLicenseNumber = pilotLicenseNumber;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
                Crew
                    ID: %s,
                    pilot license number: %s,
                    first name: %s,
                    last name: %s,
                    sex: %s,
                    birth date: %s,
                    passport country: %s,
                    passport number: %s
                """.formatted(id, pilotLicenseNumber, firstName, lastName, sex.getName(),
                birthDate, passportCountry, passportNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crew crew = (Crew) o;
        return Objects.equals(firstName, crew.firstName)
                && Objects.equals(lastName, crew.lastName)
                && Objects.equals(sex, crew.sex)
                && Objects.equals(birthDate, crew.birthDate)
                && Objects.equals(passportCountry, crew.passportCountry)
                && Objects.equals(passportNumber, crew.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, sex, birthDate, passportCountry, passportNumber);
    }
}
