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
        for(int i = 0; i < allSexes.size(); i++) {
            System.out.println((i + 1) + ": " + allSexes.get(i));
        }
    }
}
