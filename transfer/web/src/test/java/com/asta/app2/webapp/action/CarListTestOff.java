package com.asta.app2.webapp.action;

import java.util.Date;

import com.asta.app2.model.Car;
import com.asta.app2.service.CarManager;

public class CarListTestOff extends BasePageTestCase {
    private CarList bean;
    private CarManager carManager;

    public void setCarManager(CarManager carManager) {
        this.carManager = carManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new CarList();
        bean.setCarManager(carManager);
        
        // add a test car to the database
        Car car = new Car();

        // enter all required fields
        car.setCode("C1004");
        car.setPlaqueSerial("B14");
        car.setPlaqueNumber("56f565");
        car.setPlaqueIssue("Ghom");
        car.setMotor("3454354543");
        car.setChassis("42398432");
        car.setTransit("C543432Rt");
        car.setCompanyBuild("Pars Khodro");
        car.setBuildYear("1376");
        car.setInsuranceExamDeadline(new Date());
        car.setInsuranceThirdDeadline(new Date());
        car.setInsuranceBodyApartDeadline(new Date());
        
        carManager.save(car);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getCars().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}