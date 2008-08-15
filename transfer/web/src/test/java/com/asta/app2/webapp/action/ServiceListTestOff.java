package com.asta.app2.webapp.action;

import com.asta.app2.service.ServiceManager;

public class ServiceListTestOff extends BasePageTestCase {
	private ServiceList bean;
	private ServiceManager serviceManager;

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void onSetUp() throws Exception {
		super.onSetUp();
		bean = new ServiceList();
		bean.setServiceManager(serviceManager);

		// add a test service to the database
		// Service service = new Service();
		//
		// // enter all required fields
		// service.setTimed("12:30");
		// service.setDatebook(new java.util.Date());
		//
		// serviceManager.save(service);
	}

	@Override
	protected void onTearDown() throws Exception {
		super.onTearDown();
		bean = null;
	}

	public void testSearch() throws Exception {
		assertTrue(bean.getServices().size() >= 1);
		assertFalse(bean.hasErrors());
		log.debug("......"+bean.getServices().size());
	}
	public void testSearchReserve() throws Exception {
		assertTrue(bean.getServicesReadyForReserve().size() >= 0);
		assertFalse(bean.hasErrors());
		log.debug("......"+bean.getServicesReadyForReserve().size());
	}
}