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

        boolean inserted = dao.insert(element);
        if (inserted) {
            System.out.println("New element inserted successfully");
        } else {
            System.out.println("Failed to insert element");
        }
    }

    // Update element in tables
    public void updateElement(T oldElement, T newElement) {
        if(!dao.exists(oldElement)) {
            System.out.println("Element to update does not exist");
            return;
        }

        if(!isValidElement(newElement)) {
            System.out.println("Element with new properties is invalid");
            return;
        }

        if(oldElement.equals(newElement)) {
            System.out.println("Properties of old and old element are identical. Nothing to update");
            return;
        }

        boolean updated = dao.update(newElement);
        if (updated) {
            System.out.println("Element updated successfully");
        } else {
            System.out.println("Failed to update element");
        }
    }

    // Delete element from tables
    public void deleteElement(Map<String, String> fieldsForDeletion) {
        int id = dao.findId(fieldsForDeletion)
                .orElseThrow(() -> new IllegalStateException("Element does not exist"));
        boolean deleted = dao.deleteById(id);
        if (deleted) {
            System.out.println("Element deleted successfully");
        } else {
            System.out.println("Failed to delete element");
        }
    }

    public boolean isValidElement(T element) {
        Class<?> clazz = element.getClass();

        // Собираем все проверяемые колонки
        Set<String> allColumns = new HashSet<>();
        allColumns.addAll(stringFields.keySet());
        allColumns.addAll(integerFields.keySet());
        allColumns.addAll(dateFields);

        // Мапа соответствия "имя столбца БД → имя поля класса"
        // Если совпадают, можно сделать просто: Map.of("registration_number", "registrationNumber")
        Map<String, String> columnToField = dao.getColumnToFieldMap();

        for (String columnName : allColumns) {
            String fieldName = columnToField.getOrDefault(columnName, columnName);

            // Пропускаем id и всё, чего нет в классе
            if (fieldName.equalsIgnoreCase("id")) continue;

            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(element);

                if (value == null) {
                    System.out.printf("Field '%s' cannot be null%n", fieldName);
                    return false;
                }

                if (value instanceof String s) {
                    int maxLen = stringFields.getOrDefault(columnName, Integer.MAX_VALUE);
                    if (s.isEmpty()) {
                        System.out.printf("Field '%s' cannot be empty%n", fieldName);
                        return false;
                    }
                    if (s.length() > maxLen) {
                        System.out.printf("Field '%s' exceeds max length (%d)%n", fieldName, maxLen);
                        return false;
                    }
                }

                else if (value instanceof Integer i) {
                    AbstractService.IntRange range = integerFields.get(columnName);
                    if (range != null && (i < range.min() || i > range.max())) {
                        System.out.printf("Field '%s' must be between %d and %d%n", fieldName, range.min(), range.max());
                        return false;
                    }
                }

                else if (value instanceof LocalDate d) {
                    if (dateFields.contains(columnName)) {
                        // Формат LocalDate уже гарантирован Java, можно просто принять
                        // Или добавить проверку диапазона, если нужно
                    }
                }

                else {
                    // Если это вложенный объект или что-то ещё — просто пропускаем
                    continue;
                }

            } catch (NoSuchFieldException e) {
                System.out.printf("Warning: no field '%s' found in %s (expected for column '%s')%n",
                        fieldName, clazz.getSimpleName(), columnName);
            } catch (IllegalAccessException e) {
                System.out.printf("Cannot access field '%s' in class %s%n", fieldName, clazz.getSimpleName());
            }
        }

        return true;
    }

    public boolean isValidField(String fieldName, String value) {
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
            AbstractService.IntRange range = integerFields.get(fieldName);
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
