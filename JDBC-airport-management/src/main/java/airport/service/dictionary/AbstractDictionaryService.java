package airport.service.dictionary;

import airport.dao.dictionary.AbstractDictionaryDao;

import java.util.List;

public abstract class AbstractDictionaryService<T> {

    protected final AbstractDictionaryDao<T> dao;

    private final int maxLength;

    public AbstractDictionaryService(AbstractDictionaryDao<T> dao, int maxLength) {
        this.dao = dao;
        this.maxLength = maxLength;
    }

    public void showAll() {
        List<T> allElements = dao.findAll();
        System.out.println("List of elements:");
        if (!allElements.isEmpty()) {
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    public void findByName(String name) {
        T element = dao.findByName(name);
        if (element != null) {
            System.out.println("Found element: " + element);
        } else {
            System.out.println("No data found");
        }
    }

    public void add(String name) {
        if(!isNameValid(name)) return;

        if (dao.findByName(name) != null) {
            System.out.println("Element '" + name + "' already exists");
            return;
        }
        dao.insert(name);
        System.out.println("New element '" + name + "' inserted successfully");
    }

    public void update(String oldName, String newName) {
        if(!isNameValid(newName)) return;

        if(dao.findByName(newName) != null) {
            System.out.println("Element '" + newName + "' already exists");
            return;
        }
        T elementToUpdate = dao.findByName(oldName);
        if (elementToUpdate != null) {
            int id = getId(elementToUpdate);
            dao.update(id, newName);
            System.out.println("Element updated successfully");
        } else {
            System.out.println("Element '" + oldName + "' not found");
        }
    }

    public void delete(String name) {
        if(!isNameValid(name)) return;
        T elementToDelete = dao.findByName(name);
        if (elementToDelete != null) {
            int id = getId(elementToDelete);
            if (dao.deleteById(id)) {
                System.out.println("Element '" + name + "' deleted successfully");
            } else {
                System.out.println("Element '" + name + "' not found");
            }
        } else {
            System.out.println("Element '" + name + "' not found");
        }
    }

    protected abstract int getId(T entity);

    protected void printList(List<T> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
    }

    protected boolean isNameValid(String name) {
        if(name == null || name.isEmpty()) {
            System.out.println("The element name should not be NULL or empty");
            return false;
        }
        if(name.length() > maxLength) {
            System.out.println("The name entered is too long: max length is " + maxLength + " symbols");
            return false;
        }
        return true;
    }
}
