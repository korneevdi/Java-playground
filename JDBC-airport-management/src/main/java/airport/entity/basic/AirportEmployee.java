package airport.entity.basic;

import airport.entity.contact.EmergencyContact;
import airport.entity.contact.EmployeeContact;
import airport.entity.dictionary.EmployeeRole;
import airport.entity.dictionary.PersonSex;

import java.time.LocalDate;
import java.util.Objects;

public class AirportEmployee {

    private int id;
    private String firstName;
    private String lastName;
    private EmployeeRole role;
    private PersonSex sex;
    private LocalDate birthDate;
    private String passportCountry;
    private String passportNumber;
    private EmployeeContact contact;
    private EmergencyContact emergencyContact;

    public AirportEmployee(int id, String firstName, String lastName, EmployeeRole role, PersonSex sex,
                           LocalDate birthDate, String passportCountry, String passportNumber,
                           EmployeeContact contact, EmergencyContact emergencyContact) {
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

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public PersonSex getSex() {
        return sex;
    }

    public void setSex(PersonSex sex) {
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

    public EmployeeContact getContact() {
        return contact;
    }

    public void setContact(EmployeeContact contact) {
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
        return "{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role.getName() + '\'' +
                ", sex='" + sex.getName() + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", passportCountry='" + passportCountry + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", contact_email='" + contact.getEmail() + '\'' +
                ", contact_phone='" + contact.getPhone() + '\'' +
                ", city='" + contact.getCity() + '\'' +
                ", address='" + contact.getAddress() + '\'' +
                ", notes='" + contact.getNotes() + '\'' +
                ", emergency_contact_name='" + emergencyContact.getContactName() + '\'' +
                ", emergency_contact_relation='" + emergencyContact.getRelation() + '\'' +
                ", emergency_contact_phone='" + emergencyContact.getPhone() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirportEmployee that = (AirportEmployee) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(role, that.role) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(passportCountry, that.passportCountry) &&
                Objects.equals(passportNumber, that.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, role, sex, birthDate, passportCountry, passportNumber);
    }
}
