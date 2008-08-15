package com.asta.app2.webapp.action;

import com.asta.app2.model.CarKind;
import com.asta.app2.service.CarKindManager;

/**
 * 
 * @author  <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CarKindListTestOff extends BasePageTestCase {
    private CarKindList bean;
    private CarKindManager carKindManager;

    public void setCarKindManager(CarKindManager carKindManager) {
        this.carKindManager = carKindManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new CarKindList();
        bean.setCarKindManager(carKindManager);
        
        // add a test carKind to the database
        CarKind carKind = new CarKind();

        // enter all required fields
        carKind.setName("RuWgKiLwVcYxHxRu");
        carKind.setCapacity(40);

        carKindManager.save(carKind);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getCarKinds().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}