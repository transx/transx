package com.asta.app2.webapp.action;

import com.asta.app2.model.Passenger;
import com.asta.app2.service.PassengerManager;

public class PassengerFormTestOff extends BasePageTestCase {
    private PassengerForm bean;
    private PassengerManager passengerManager;
        
    public void setPassengerManager(PassengerManager passengerManager) {
        this.passengerManager = passengerManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new PassengerForm();
        bean.setPassengerManager(passengerManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }
/*
    public void testAdd() throws Exception {
        Passenger passenger = new Passenger();

        // enter all required fields
        passenger.setLastName("Kazemi");
        passenger.setGender(Gender.FEMALE);
        bean.setPassenger(passenger);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getPassenger());
        assertFalse(bean.hasErrors());
    }

    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getPassenger());
        Passenger passenger = bean.getPassenger();

        // update required fields
        passenger.setLastName("Rahmati");
        passenger.setGender(Gender.FEMALE);
        bean.setPassenger(passenger);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }*/

    public void testRemove() throws Exception {
        log.debug("testing remove...");
        Passenger passenger = new Passenger();
        passenger.setId(-2L);
        bean.setPassenger(passenger);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}