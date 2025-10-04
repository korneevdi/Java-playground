package airport.service.basic;

import airport.dao.basic.CrewDao;
import airport.entity.basic.Crew;

public class CrewService extends AbstractBasicService<Crew>{

    public CrewService(CrewDao crewDao) {
        super(crewDao);
    }
}
