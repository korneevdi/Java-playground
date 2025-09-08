package airport.service;

import airport.dao.CrewRolesDao;

import java.util.List;

public class CrewRolesService {

    private final CrewRolesDao crewRolesDao;

    public CrewRolesService(CrewRolesDao crewRolesDao) {
        this.crewRolesDao = crewRolesDao;
    }

    public void showAllRoles() {
        List<String> allRoles = crewRolesDao.findAll();
        System.out.println("List of crew roles:");
        if(allRoles == null || allRoles.size() == 0) {
            System.out.println("No data found");
        }
        for(int i = 0; i < allRoles.size(); i++) {
            System.out.println((i + 1) + ": " + allRoles.get(i));
        }
    }

    public void findRole(int id) {
        String role = crewRolesDao.findById(id);
        if(!role.isEmpty()) {
            System.out.println("Role: " + role);
        } else {
            System.out.println("No data found");
        }
    }

    public void addRole(String role) {
        if(role != null || !role.isEmpty()) {
            crewRolesDao.insert(role);
            System.out.println("New element '" + role + "' inserted successfully");
        } else {
            System.out.println("New element should not be NULL or empty");
        }
    }

    public void updateRole(String oldRole, String newRole) {
        if(oldRole != null && newRole != null && !oldRole.isEmpty() && !newRole.isEmpty()) {
            int id = crewRolesDao.findId(oldRole);
            if(id != -1) {
                crewRolesDao.update(id, newRole);
                System.out.println("Role updated successfully");
            } else{
                System.out.println("Element '" + oldRole + "' not found");
            }
        } else {
            System.out.println("'oldRole' and 'newRole' should not be NULL or empty");
        }
    }

    public void deleteRole(String role) {
        if(role != null && !role.isEmpty()) {
            crewRolesDao.delete(role);
            System.out.println("Role '" + role + "' deleted successfully");
        } else{
            System.out.println("Role should not be NULL or empty");
        }
    }
}
