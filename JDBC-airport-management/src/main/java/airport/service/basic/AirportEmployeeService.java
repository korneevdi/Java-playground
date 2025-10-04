package airport.service.basic;

import airport.dao.basic.AirportEmployeeDao;
import airport.entity.basic.AirportEmployee;

public class AirportEmployeeService extends AbstractBasicService<AirportEmployee>{

    public AirportEmployeeService(AirportEmployeeDao airportEmployeeDao) {
        super(airportEmployeeDao);
    }
}
