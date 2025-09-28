package airport.entity.contact;

import java.util.Objects;

public class AirlineContact {

    private int id;

    private String contactName;

    private String email;

    private String phone;

    private String city;

    private String notes;

    public AirlineContact(int id, String contactName, String email, String phone, String city, String notes) {
        this.id = id;
        this.contactName = contactName;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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
                ", contactName='" + contactName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirlineContact contact = (AirlineContact) o;
        return Objects.equals(contactName, contact.contactName) && Objects.equals(email, contact.email) && Objects.equals(phone, contact.phone) && Objects.equals(city, contact.city) && Objects.equals(notes, contact.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactName, email, phone, city, notes);
    }
}
