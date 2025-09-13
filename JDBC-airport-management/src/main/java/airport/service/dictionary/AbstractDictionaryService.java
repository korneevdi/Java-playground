package airport.service.dictionary;

import airport.dao.dictionary.AbstractDictionaryDao;

import java.util.List;

public abstract class AbstractDictionaryService<T> {

    protected final AbstractDictionaryDao<T> dao;

    public AbstractDictionaryService(AbstractDictionaryDao<T> dao) {
        this.dao = dao;
    }

    public void showAll() {
        List<T> allElements = dao.findAll();
        System.out.println("List of elements:");
        if (!allElements.isEmpty()) {
            for (int i = 0; i < allElements.size(); i++) {
                System.out.println((i + 1) + ": " + allElements.get(i));
            }
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
        if (name != null && !name.isEmpty()) {
            if (dao.findByName(name) != null) {
                System.out.println("Element '" + name + "' already exists");
                return;
            }
            dao.insert(name);
            System.out.println("New element '" + name + "' inserted successfully");
        } else {
            System.out.println("New element should not be NULL or empty");
        }
    }

    public void update(String oldName, String newName) {
        if (oldName != null && newName != null && !oldName.isEmpty() && !newName.isEmpty()) {
            if (dao.findByName(newName) == null) {
                T elementToUpdate = dao.findByName(oldName);
                if (elementToUpdate != null) {
                    int id = getId(elementToUpdate);
                    dao.update(id, newName);
                    System.out.println("Element updated successfully");
                } else {
                    System.out.println("Element '" + oldName + "' not found");
                }
            } else {
                System.out.println("Element '" + newName + "' already exists");
            }
        } else {
            System.out.println("'oldClaim' and 'newClaim' should not be NULL or empty");
        }
    }

    public void delete(String name) {
        if (name != null && !name.isEmpty()) {
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
        } else {
            System.out.println("Name should not be NULL or empty");
        }
    }

    protected abstract int getId(T entity);
}
