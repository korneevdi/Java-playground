package airport.service.basic;

import airport.dao.basic.AbstractBasicDao;

import java.util.List;

public abstract class AbstractBasicService<T> {

    protected final AbstractBasicDao<T> dao;

    //protected final Map<String, Integer> fieldMaxLengths = new HashMap<>();

    public AbstractBasicService(AbstractBasicDao<T> dao) {
        this.dao = dao;
    }

    // Show all saved contacts
    public void showAll() {
        List<T> allElements = dao.findAll();
        System.out.println("List of elements:");
        if (!allElements.isEmpty()) {
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    protected void printList(List<T> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
    }
/*
    // Add new contact
    public void addContact(T entity) {
        if(!isValidContact(entity)) return;

        if (dao.exists(entity)) {
            existsOrNotOutput(entity, true);
            return;
        }

        dao.insert(entity);
        System.out.println("New element inserted successfully");
    }

    // Update contact
    public void updateElement(T oldEntity, T newEntity) {
        if (!dao.exists(oldEntity)) {
            existsOrNotOutput(oldEntity, false);
            return;
        }

        if (areContactsIdentical(oldEntity, newEntity)) {
            System.out.println("The old and new properties are identical. Nothing to update.");
            return;
        }

        if (!isValidContact(newEntity)) return;

        boolean updated = dao.update(newEntity);
        if (updated) {
            System.out.println("Element updated successfully");
        } else {
            System.out.println("Failed to update element");
        }
    }

    // Delete contact
    public void deleteContact(T contact) {
        if (!dao.exists(contact)) {
            existsOrNotOutput(contact, false);
            return;
        }

        int id = findId(contact)
                .orElseThrow(() -> new RuntimeException("Could not find element"));

        boolean deleted = dao.deleteById(id);

        if (deleted) {
            System.out.println("Element deleted successfully");
        } else {
            System.out.println("Failed to delete element");
        }
    }

    protected boolean validateField(String fieldName, String value) {
        if(value == null || value.isBlank()) {
            System.out.printf("Field '%s' must not be null or empty%n", fieldName);
            return false;
        }
        Integer maxLength = fieldMaxLengths.get(fieldName);
        if(maxLength != null && value.length() > maxLength) {
            System.out.printf("Field '%s' is too long (max %d symbols)%n", fieldName, maxLength);
            return false;
        }
        return true;
    }

    protected abstract boolean isValidContact(T entity);

    protected abstract void existsOrNotOutput(T entity, boolean isExists);

    protected abstract boolean areContactsIdentical(T oldEntity, T newEntity);

    protected abstract Optional<Integer> findId(T entity);*/
}
