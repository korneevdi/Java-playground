package airport.entity.basic;

import airport.entity.contact.AirportEmployeeContact;
import airport.entity.contact.EmergencyContact;
import airport.entity.dictionary.AirportEmployeeRole;
import airport.entity.dictionary.Sex;

import java.time.LocalDate;
import java.util.Objects;

public class AirportEmployee {

    private int id;
    private String firstName;
    private String lastName;
    private AirportEmployeeRole role;
    private Sex sex;
    private LocalDate birthDate;
    private String passportCountry;
    private String passportNumber;
    private AirportEmployeeContact contact;
    private EmergencyContact emergencyContact;

    public AirportEmployee(int id, String firstName, String lastName, AirportEmployeeRole role, Sex sex,
                           LocalDate birthDate, String passportCountry, String passportNumber,
                           AirportEmployeeContact contact, EmergencyContact emergencyContact) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.sex = sex;
        this.birthDate = birthDate;
        this.passportCountry = passportCountry;
        this.passportNumber = passportNumber;
        this.contact = contact;
        this.emergencyContact = emergencyContact;
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

    public AirportEmployeeRole getRole() {
        return role;
    }

    public void setRole(AirportEmployeeRole role) {
        this.role = role;
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

    public AirportEmployeeContact getContact() {
        return contact;
    }

    public void setContact(AirportEmployeeContact contact) {
        this.contact = contact;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    @Override
    public String toString() {
        return """
                AirportEmployee
                    ID: %s,
                    first name: %s,
                    last name: %s,
                    role: %s,
                    sex: %s,
                    birth date: %s,
                    passport country: %s,
                    passport number: %s,
                    contact email: %s,
                    contact phone: %s,
                    city: %s,
                    address: %s,
                    notes: %s,
                    emergency contact name: %s,
                    emergency contact relation: %s,
                    emergency contact phone: %s
                """.formatted(id, firstName, lastName, role.getName(), sex.getName(), birthDate,
                passportCountry, passportNumber, contact.getEmail(), contact.getPhone(),
                contact.getCity(), contact.getAddress(), contact.getNotes(),
                emergencyContact.getContactName(), emergencyContact.getRelation(), emergencyContact.getPhone());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirportEmployee that = (AirportEmployee) o;
        return Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(role, that.role)
                && Objects.equals(sex, that.sex)
                && Objects.equals(birthDate, that.birthDate)
                && Objects.equals(passportCountry, that.passportCountry)
                && Objects.equals(passportNumber, that.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, role, sex, birthDate, passportCountry, passportNumber);
    }
}
