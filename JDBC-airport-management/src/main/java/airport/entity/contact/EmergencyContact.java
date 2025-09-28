package airport.entity.contact;

import java.util.Objects;

public class EmergencyContact {

    private int id;

    private String contactName;

    private String relation;

    private String phone;

    public EmergencyContact(int id, String contactName, String relation, String phone) {
        this.id = id;
        this.contactName = contactName;
        this.relation = relation;
        this.phone = phone;
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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", contactName='" + contactName + '\'' +
                ", relation='" + relation + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmergencyContact that = (EmergencyContact) o;
        return Objects.equals(contactName, that.contactName) && Objects.equals(relation, that.relation) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactName, relation, phone);
    }
}
