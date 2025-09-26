package airport.service.contact;

import airport.dao.contact.EmployeeContactsDao;
import airport.entity.contact.EmployeeContact;

import java.util.List;
import java.util.Optional;

public class EmployeeContactsService extends AbstractContactService<EmployeeContact> {

    private final EmployeeContactsDao employeeContactsDao;

    public EmployeeContactsService(EmployeeContactsDao dao) {
        super(dao);
        this.employeeContactsDao = dao;

        // Set the map of the fields and max lengths
        fieldMaxLengths.put("email", 100);
        fieldMaxLengths.put("phone", 30);
        fieldMaxLengths.put("city", 25);
        fieldMaxLengths.put("address", 200);
    }

    // Contact with certain email
    public void findElementByEmail(String email) {
        Optional<EmployeeContact> contact = employeeContactsDao.findByEmail(email);
        if (contact.isPresent()) {
            System.out.println("Contact found with email '" + email + "':");
            System.out.println(contact.get());
        } else {
            System.out.println("No data found");
        }
    }

    // All contacts with certain phone
    public void findAllElementsByPhone(String phone) {
        List<EmployeeContact> allElements = employeeContactsDao.findAllByPhone(phone);
        if (!allElements.isEmpty()) {
            System.out.println("All contacts found with phone '" + phone + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // All contacts with certain city
    public void findAllElementsByCity(String city) {
        List<EmployeeContact> allElements = employeeContactsDao.findAllByCity(city);
        if (!allElements.isEmpty()) {
            System.out.println("All contacts found from '" + city + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // All contacts with certain address
    public void findAllElementsByAddress(String address) {
        List<EmployeeContact> allElements = employeeContactsDao.findAllByAddress(address);
        if (!allElements.isEmpty()) {
            System.out.println("All contacts found with address '" + address + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // Add new contact
    public void add(String email, String phone, String city, String address, String notes) {
        EmployeeContact contact = new EmployeeContact(0, email, phone, city, address, notes);
        addContact(contact);
    }

    // Update contact
    public void update(String oldEmail, String oldPhone, String oldCity, String oldAddress, String oldNotes,
                       String newEmail, String newPhone, String newCity, String newAddress, String newNotes) {

        EmployeeContact oldContact = new EmployeeContact(0, oldEmail, oldPhone, oldCity, oldAddress, oldNotes);

        int id = employeeContactsDao.findByEmail(oldContact.getEmail())
                .orElseThrow(() -> new RuntimeException("Contact to update is not found"))
                .getId();

        EmployeeContact newContact = new EmployeeContact(id, newEmail, newPhone, newCity, newAddress, newNotes);

        updateContact(oldContact, newContact);
    }

    // Delete contact
    public void delete(String email, String phone, String city, String address) {
        EmployeeContact contact = new EmployeeContact(0, email, phone, city, address, "");
        deleteContact(contact);
    }

    @Override
    protected boolean isValidContact(EmployeeContact contact) {
        return validateField("email", contact.getEmail()) &&
                validateField("phone", contact.getPhone()) &&
                validateField("city", contact.getCity()) &&
                validateField("address", contact.getAddress());
    }

    @Override
    protected void existsOrNotOutput(EmployeeContact contact, boolean isExists) {
        String output =
                """
                Element with the following properties

                email: %s,
                phone: %s,
                city: %s,
                address: %s
                """.formatted(contact.getEmail(), contact.getPhone(), contact.getCity(), contact.getAddress());

        if(isExists) {
            output = output + "\nalready exists.";
        } else {
            output = output + "\ndoes not exist.";
        }
        System.out.println(output);
    }

    @Override
    protected boolean areContactsIdentical(EmployeeContact oldContact, EmployeeContact newContact) {
        return oldContact.equals(newContact);
    }

    @Override
    protected Optional<Integer> findId(EmployeeContact contact) {
        return employeeContactsDao.findByEmail(contact.getEmail()).map(EmployeeContact::getId);
    }
}
