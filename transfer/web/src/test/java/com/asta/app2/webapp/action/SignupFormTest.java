package com.asta.app2.webapp.action;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.context.SecurityContextHolder;
import org.subethamail.wiser.Wiser;

import com.asta.app2.Constants;
import com.asta.app2.model.Address;
import com.asta.app2.model.User;
import com.asta.app2.service.CompanyManager;
import com.asta.app2.service.MailEngine;
import com.asta.app2.service.RoleManager;
import com.asta.app2.service.UserManager;

public class SignupFormTest extends BasePageTestCase {
    private SignupForm bean;

    @Override
    public void onSetUp() throws Exception {
        super.onSetUp();
        bean = new SignupForm();
        bean.setUserManager((UserManager) applicationContext.getBean("userManager"));
        bean.setRoleManager((RoleManager) applicationContext.getBean("roleManager"));
        bean.setMessage((SimpleMailMessage) applicationContext.getBean("mailMessage"));
        bean.setMailEngine((MailEngine) applicationContext.getBean("mailEngine"));
        bean.setTemplateName("accountCreated.vm");
        bean.setCompanyManager((CompanyManager) applicationContext.getBean("companyManager"));
    }

    public void testExecute() throws Exception {
        User user = new User("self-registered");
        user.setPassword("Password1");
        user.setConfirmPassword("Password1");
        user.setFirstName("First");
        user.setLastName("Last");

        Address address = new Address();
        address.setCity("Denver");
        address.setProvince("CO");
        address.setCountry("USA");
        address.setPostalCode("80210");
        user.setAddress(address);

        user.setEmail("saeed.morad@gmail.com");
        user.setWebsite("http://raibledesigns.com");
        user.setPasswordHint("Password is one with you.");
        
        bean.setCompanyID("-1");
        bean.setUser(user);

       // start SMTP Server
        Wiser wiser = new Wiser();
        wiser.setPort(getSmtpPort());
        wiser.start();

        assertEquals("mainMenu", bean.save());
        assertFalse(bean.hasErrors());

        // verify an account information e-mail was sent
        wiser.stop();
        assertTrue(wiser.getMessages().size() == 1);

        // verify that success messages are in the session
        assertNotNull(bean.getSession().getAttribute(Constants.REGISTERED));

        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
