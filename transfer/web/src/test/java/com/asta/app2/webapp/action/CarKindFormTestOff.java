package com.asta.app2.webapp.action;

import com.asta.app2.model.CarKind;
import com.asta.app2.service.CarKindManager;

public class CarKindFormTestOff extends BasePageTestCase {
    private CarKindForm bean;
    private CarKindManager carKindManager;
        
    public void setCarKindManager(CarKindManager carKindManager) {
        this.carKindManager = carKindManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new CarKindForm();
        bean.setCarKindManager(carKindManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }
/*
    public void testAdd() throws Exception {
        CarKind carKind = new CarKind();

        // enter all required fields
        carKind.setName("" + Math.random());
        carKind.setCapacity(1159061793);
        bean.setCarKind(carKind);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getCarKind());
        assertFalse(bean.hasErrors());
    }

    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getCarKind());
        CarKind carKind = bean.getCarKind();

        // update required fields
        carKind.setName("SjLkOlHeTkMqYiVqAm");
        carKind.setCapacity(48);
        bean.setCarKind(carKind);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }*/

    public void testRemove() throws Exception {
        log.debug("testing remove...");
        CarKind carKind = new CarKind();
        carKind.setId(-2L);
        bean.setCarKind(carKind);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}