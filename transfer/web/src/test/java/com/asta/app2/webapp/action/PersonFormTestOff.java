package com.asta.app2.webapp.action;

import com.asta.app2.model.Person;
import com.asta.app2.service.PersonManager;

public class PersonFormTestOff extends BasePageTestCase {
    private PersonForm bean;
    private PersonManager personManager;
        
    public void setPersonManager(PersonManager personManager) {
        this.personManager = personManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new PersonForm();
        bean.setPersonManager(personManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }
/*
    public void testAdd() throws Exception {
        Person person = new Person();

        // enter all required fields
        person.setFirstName("+YxDaNkBqShWnPi");
        person.setLastName("+FjKKuZlVzPo");
        person.setFatherName("+QmBpAUpAf");
        person.setGender(Gender.FEMALE);
        person.setBirthday(new java.util.Date());
        person.setIdentityNumber("+TuDvYlMuAmDi");
        person.setIdentityIssue("+JzIlVnNwEsWyIv");
        bean.setPerson(person);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getPerson());
        assertFalse(bean.hasErrors());
    }

    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getPerson());
        Person person = bean.getPerson();

        // update required fields
        person.setFirstName("_YrThJtAuWjJzHz");
        person.setLastName("_QoIjXtYbYhPr");
        person.setFatherName("_IrDqIuSsDbGaMsMf");
        person.setGender(Gender.FEMALE);
        person.setBirthday(new java.util.Date());
        person.setIdentityNumber("_BuCpQzWwCmWhVyPx");
        person.setIdentityIssue("_NgErItUdZuDy");
        bean.setPerson(person);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }
*/
    public void testRemove() throws Exception {
        log.debug("testing remove...");
        Person person = new Person();
        person.setId(-2L);
        bean.setPerson(person);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}