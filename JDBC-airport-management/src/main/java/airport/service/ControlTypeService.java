package airport.service;

import airport.dao.ControlTypesDao;

import java.util.List;

public class ControlTypeService {

    private final ControlTypesDao controlTypesDao;

    public ControlTypeService(ControlTypesDao controlTypesDao) {
        this.controlTypesDao = controlTypesDao;
    }

    public void showAllTypes() {
        List<String> allTypes = controlTypesDao.findAll();
        System.out.println("List of control types:");
        if(allTypes == null || allTypes.size() == 0) {
            System.out.println("No data found");
        }
        for(int i = 0; i < allTypes.size(); i++) {
            System.out.println((i + 1) + ": " + allTypes.get(i));
        }
    }

    public void findType(int id) {
        String type = controlTypesDao.findById(id);
        if(!type.isEmpty()) {
            System.out.println("Control type: " + type);
        } else {
            System.out.println("No data found");
        }
    }

    public void addType(String type) {
        if(type != null || !type.isEmpty()) {
            controlTypesDao.insert(type);
            System.out.println("New element '" + type + "' inserted successfully");
        } else {
            System.out.println("New element should not be NULL or empty");
        }
    }

    public void updateType(String oldType, String newType) {
        if(oldType != null && newType != null && !oldType.isEmpty() && !newType.isEmpty()) {
            int id = controlTypesDao.findId(oldType);
            if(id != -1) {
                controlTypesDao.update(id, newType);
                System.out.println("Control type updated successfully");
            } else{
                System.out.println("Element '" + oldType + "' not found");
            }
        } else {
            System.out.println("'oldType' and 'newType' should not be NULL or empty");
        }
    }

    public void deleteType(String type) {
        if(type != null && !type.isEmpty()) {
            if(controlTypesDao.delete(type)) {
                System.out.println("Control type '" + type + "' deleted successfully");
            } else {
                System.out.println("Element '" + type + "' not found");
            }
        } else{
            System.out.println("Control type should not be NULL or empty");
        }
    }
}
