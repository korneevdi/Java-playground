package airport.service;

import airport.dao.AbstractDao;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public abstract class AbstractService<T> {

    protected final AbstractDao<T> dao;
    private final String entityName;

    protected Map<String, Integer> stringFields = new HashMap<>();
    protected Map<String, IntRange> integerFields = new HashMap<>();
    protected Set<String> dateFields = new HashSet<>();

    public AbstractService(AbstractDao<T> dao, String entityName) {
        this.dao = dao;
        this.entityName = entityName;
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

    // Find element(s) by field
    public void findAllByField(String fieldName, Object fieldValue) {
        if(!isValidField(fieldName, fieldValue.toString())) return;

        Object realValue = fieldValue;
        if (dateFields.contains(fieldName) && fieldValue instanceof String) {
            LocalDate localDate = LocalDate.parse((String) fieldValue, DateTimeFormatter.ISO_LOCAL_DATE);
            realValue = java.sql.Date.valueOf(localDate);
        }

        List<T> allElements = dao.findByField(fieldName, realValue);
        if(allElements.isEmpty()) {
            System.out.println("No data found");
        } else {
            printList(allElements);
        }
    }

    // Insert new element into tables
    public void addElement(T element) {
        if(!isValidElement(element)) {
            System.out.println("Invalid input");
            return;
        }

        if (dao.exists(element)) {
            System.out.println("Element with properties entered already exists");
            return;
        }

        dao.insert(element);
        System.out.println("New element inserted successfully");
    }

    protected boolean isValidElement(T element) {
        Class<?> clazz = element.getClass();

        // Gather all fields, which must be checked
        Set<String> allFields = new HashSet<>();
        allFields.addAll(stringFields.keySet());
        allFields.addAll(integerFields.keySet());
        allFields.addAll(dateFields);

        for (String fieldName : allFields) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(element);
                String stringValue = value != null ? value.toString() : "";

                if (!isValidField(fieldName, stringValue)) {
                    return false;
                }
            } catch (NoSuchFieldException e) {
                System.out.println("Warning: field '" + fieldName + "' not found in class " + clazz.getSimpleName());
            } catch (IllegalAccessException e) {
                System.out.println("Cannot access field '" + fieldName + "' in class " + clazz.getSimpleName());
            }
        }

        return true;
    }

    protected boolean isValidField(String fieldName, String value) {
        if (stringFields.containsKey(fieldName)) {
            return isValidString(fieldName, value);
        } else if (integerFields.containsKey(fieldName)) {
            return isValidInt(fieldName, value);
        } else if (dateFields.contains(fieldName)) {
            return isValidDate(value);
        } else {
            System.out.println("The " + entityName + " entity does not contain property '" + fieldName + "'");
            return false;
        }
    }

    private boolean isValidString(String fieldName, String value) {
        int maxLength = stringFields.get(fieldName);
        if (value == null || value.isEmpty()) {
            System.out.println("Field '" + fieldName + "' cannot be empty");
            return false;
        }
        if (value.length() > maxLength) {
            System.out.println("Field '" + fieldName + "' exceeds max length (" + maxLength + ")");
            return false;
        }
        return true;
    }

    private boolean isValidInt(String fieldName, String value) {
        try {
            int intValue = Integer.parseInt(value);
            IntRange range = integerFields.get(fieldName);
            if (intValue < range.min() || intValue > range.max()) {
                System.out.println("Field '" + fieldName + "' must be between " + range.min() + " and " + range.max());
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Field '" + fieldName + "' must be a number");
            return false;
        }
    }

    private boolean isValidDate(String value) {
        try {
            LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format (expected yyyy-MM-dd)");
            return false;
        }
    }

    protected void printList(List<T> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
    }

    public LocalDate parseDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try {
            return LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd.MM.yyyy");
            return null;
        }
    }

    // Auxiliary class for integer range
    public record IntRange(int min, int max) {}

/*
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

    protected abstract void existsOrNotOutput(T entity, boolean isExists);

    protected abstract boolean areContactsIdentical(T oldEntity, T newEntity);

    protected abstract Optional<Integer> findId(T entity);*/
}
