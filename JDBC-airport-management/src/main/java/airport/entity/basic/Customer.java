package airport.entity.basic;

import airport.entity.contact.CustomerContact;

import java.util.Objects;

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String passportCountry;
    private String passportNumber;
    private CustomerContact contact;

    public Customer(int id, String firstName, String lastName, String passportCountry,
                    String passportNumber, CustomerContact contact) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportCountry = passportCountry;
        this.passportNumber = passportNumber;
        this.contact = contact;
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

    public CustomerContact getContact() {
        return contact;
    }

    public void setContact(CustomerContact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportCountry='" + passportCountry + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", contact_email=" + contact.getEmail() + '\'' +
                ", contact_phone=" + contact.getPhone() + '\'' +
                ", city=" + contact.getCity() + '\'' +
                ", address=" + contact.getAddress() + '\'' +
                ", notes=" + contact.getNotes() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(passportCountry, customer.passportCountry) &&
                Objects.equals(passportNumber, customer.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, passportCountry, passportNumber);
    }
}
