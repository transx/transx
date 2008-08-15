package com.asta.app2.webapp.action;

import com.asta.app2.model.Person;
import com.asta.app2.model.enums.Gender;
import com.asta.app2.service.PersonManager;

public class PersonListTestOff extends BasePageTestCase {
    private PersonList bean;
    private PersonManager personManager;

    public void setPersonManager(PersonManager personManager) {
        this.personManager = personManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new PersonList();
        bean.setPersonManager(personManager);
        
        // add a test person to the database
        Person person = new Person();

        // enter all required fields
        person.setFirstName("FpCmVk");
        person.setLastName("ApDEn");
        person.setFatherName("YYoJyElExKaUx");
        person.setGender(Gender.MALE);
        person.setBirthday(new java.util.Date());
        person.setIdentityNumber("NvJzGeZqQe");
        person.setIdentityIssue("CzMxNyO");

        personManager.save(person);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getPersons().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}