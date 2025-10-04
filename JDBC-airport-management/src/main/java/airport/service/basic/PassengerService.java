package airport.service.basic;

import airport.dao.basic.PassengerDao;
import airport.entity.basic.Passenger;

public class PassengerService extends AbstractBasicService<Passenger>{

    public PassengerService(PassengerDao passengerDao) {
        super(passengerDao);
    }
}
