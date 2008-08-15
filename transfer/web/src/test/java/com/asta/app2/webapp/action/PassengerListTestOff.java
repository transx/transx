package com.asta.app2.webapp.action;

import com.asta.app2.model.Passenger;
import com.asta.app2.model.enums.Gender;
import com.asta.app2.service.PassengerManager;

public class PassengerListTestOff extends BasePageTestCase {
    private PassengerList bean;
    private PassengerManager passengerManager;

    public void setPassengerManager(PassengerManager passengerManager) {
        this.passengerManager = passengerManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new PassengerList();
        bean.setPassengerManager(passengerManager);
        
        // add a test passenger to the database
        Passenger passenger = new Passenger();

        // enter all required fields
        passenger.setLastName("karimi");
        passenger.setGender(Gender.FEMALE);

        passengerManager.save(passenger);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getPassengers().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}