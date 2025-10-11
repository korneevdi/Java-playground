package airport.service.dictionary;

import airport.dao.dictionary.AbstractDictionaryDao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public abstract class AbstractDictionaryService<T> {

    protected final AbstractDictionaryDao<T> dao;

    private final String entityName;

    protected Map<String, Integer> stringFields = new HashMap<>();
    protected Map<String, AbstractDictionaryService.IntRange> integerFields = new HashMap<>();
    protected Set<String> dateFields = new HashSet<>();

    public AbstractDictionaryService(AbstractDictionaryDao<T> dao, String entityName) {
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
        if(!validateField(fieldName, fieldValue.toString())) return;

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

    protected boolean validateField(String fieldName, String value) {
        if (stringFields.containsKey(fieldName)) {
            return validateString(fieldName, value);
        } else if (integerFields.containsKey(fieldName)) {
            return validateInt(fieldName, value);
        } else if (dateFields.contains(fieldName)) {
            return validateDate(value);
        } else {
            System.out.println("The " + entityName + " entity does not contain property '" + fieldName + "'");
            return false;
        }
    }

    private boolean validateString(String fieldName, String value) {
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

    private boolean validateInt(String fieldName, String value) {
        try {
            int intValue = Integer.parseInt(value);
            AbstractDictionaryService.IntRange range = integerFields.get(fieldName);
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

    private boolean validateDate(String value) {
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

    // Auxiliary class for integer range
    protected record IntRange(int min, int max) {}
}
