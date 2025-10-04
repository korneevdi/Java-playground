package airport.service.basic;

import airport.dao.basic.AirplaneDao;
import airport.entity.basic.Airplane;

public class AirplaneService extends AbstractBasicService<Airplane>{

    public AirplaneService(AirplaneDao airplaneDao) {
        super(airplaneDao);
    }
}
