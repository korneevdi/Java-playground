package airport.service;

import airport.dao.TypeDao;

import java.util.List;

public class TypeService {

    private final TypeDao typeDao;

    public TypeService(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    public void showAllTypes() {
        List<String> allTypes = typeDao.findAll();
        System.out.println("List of types:");
        if(allTypes != null && allTypes.size() != 0) {
            for(int i = 0; i < allTypes.size(); i++) {
                System.out.println((i + 1) + ": " + allTypes.get(i));
            }
        } else {
            System.out.println("No data found");
        }
    }

    public void findType(int id) {
        String type = typeDao.findById(id);
        if(!type.isEmpty()) {
            System.out.println("Type: " + type);
        } else {
            System.out.println("No data found");
        }
    }

    public void addType(String type) {
        if(type != null && !type.isEmpty()) {
            List<String> allTypes = typeDao.findAll();
            if(!allTypes.contains(type)) {
                typeDao.insert(type);
                System.out.println("New element '" + type + "' inserted successfully");
            } else {
                System.out.println("Element '" + type + "' already exists");
            }
        } else {
            System.out.println("New element should not be NULL or empty");
        }
    }

    public void updateType(String oldType, String newType) {
        if(oldType != null && newType != null && !oldType.isEmpty() && !newType.isEmpty()) {
            int id = typeDao.findId(oldType);
            if(id != -1) {
                typeDao.update(id, newType);
                System.out.println("Type updated successfully");
            } else{
                System.out.println("Element '" + oldType + "' not found");
            }
        } else {
            System.out.println("'oldType' and 'newType' should not be NULL or empty");
        }
    }

    public void deleteType(String type) {
        if(type != null && !type.isEmpty()) {
            if(typeDao.delete(type)) {
                System.out.println("Type '" + type + "' deleted successfully");
            } else {
                System.out.println("Element '" + type + "' not found");
            }
        } else{
            System.out.println("Type should not be NULL or empty");
        }
    }
}
