package airport.entity.contact;

import java.util.Objects;

public class CustomerContact {

    private int id;

    private String email;

    private String phone;

    private String city;

    private String address;

    private String notes;

    public CustomerContact(int id, String email, String phone, String city, String address, String notes) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.address = address;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerContact that = (CustomerContact) o;
        return Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(city, that.city) && Objects.equals(address, that.address) && Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone, city, address, notes);
    }
}
