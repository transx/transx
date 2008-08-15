package com.asta.app2.webapp.action;

import com.asta.app2.model.Car;
import com.asta.app2.service.CarManager;

public class CarFormTestOff extends BasePageTestCase {
    private CarForm bean;
    private CarManager carManager;
        
    public void setCarManager(CarManager carManager) {
        this.carManager = carManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new CarForm();
        bean.setCarManager(carManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

  /*  public void testAdd() throws Exception {
        Car car = new Car();

        // enter all required fields
        car.setCode("" + Math.random());
        car.setPlaqueSerial("P17");
        car.setPlaqueNumber("944324");
        car.setPlaqueIssue("Ahvaz");
        car.setMotor("345433");
        car.setChassis("2132133");
        car.setTransit("E3222T3");
        car.setCompany("Sahand");
        car.setBuildYear("1387");
        bean.setCar(car);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getCar());
        assertFalse(bean.hasErrors());
    }
   */

 /*   public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getCar());
        Car car = bean.getCar();

        // update required fields
        car.setCode("C1006");
        car.setPlaqueSerial("M34");
        car.setPlaqueNumber("23432432");
        car.setPlaqueIssue("Tehran");
        car.setMotor("456745643");
        car.setChassis("24355654");
        car.setTransit("E465645");
        car.setCompany("ایران خودرو");
        car.setBuildYear("1370");
        bean.setCar(car);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }*/

    public void testRemove() throws Exception {
        log.debug("testing remove...");
        Car car = new Car();
        car.setId(-2L);
        bean.setCar(car);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}