package airport.service;

import airport.dao.PersonSexDao;

import java.util.List;

public class PersonSexService {

    private final PersonSexDao personSexDao;

    public PersonSexService(PersonSexDao personSexDao) {
        this.personSexDao = personSexDao;
    }

    public void showAllSexes() {
        List<String> allSexes = personSexDao.findAll();
        System.out.println("List of sexes:");
        if(allSexes == null || allSexes.size() == 0) {
            System.out.println("No data found");
        }
        for(int i = 0; i < allSexes.size(); i++) {
            System.out.println((i + 1) + ": " + allSexes.get(i));
        }
    }

    public void findSex(int id) {
        String sex = personSexDao.findById(id);
        if(!sex.isEmpty()) {
            System.out.println("Sex: " + sex);
        } else {
            System.out.println("No data found");
        }
    }

    public void addSex(String sex) {
        if(sex != null || !sex.isEmpty()) {
            personSexDao.insert(sex);
            System.out.println("New element '" + sex + "' inserted successfully");
        } else {
            System.out.println("New element should not be NULL or empty");
        }
    }

    public void updateSex(String oldSex, String newSex) {
        if(oldSex != null && newSex != null && !oldSex.isEmpty() && !newSex.isEmpty()) {
            int id = personSexDao.findId(oldSex);
            if(id != -1) {
                personSexDao.update(id, newSex);
                System.out.println("Sex updated successfully");
            } else{
                System.out.println("Element '" + oldSex + "' not found");
            }
        } else {
            System.out.println("'oldSex' and 'newSex' should not be NULL or empty");
        }
    }

    public void deleteSex(String sex) {
        if(sex != null && !sex.isEmpty()) {
            personSexDao.delete(sex);
            System.out.println("Sex '" + sex + "' deleted successfully");
        } else{
            System.out.println("Sex should not be NULL or empty");
        }
    }
}
