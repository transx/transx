/* *Class created on [ Jun 7, 2008 | 6:17:05 PM ] */
package com.asta.app2.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.asta.app2.model.CarKind;
import com.asta.app2.model.Driver;
import com.asta.app2.model.Service;
import com.asta.app2.model.enums.Weekday;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ServiceDaoTest extends BaseDaoTestCase {
	private ServiceDao serviceDao;
	private DriverDao driverDao;
	private CarKindDao carKindDao;
	
	public void setCarKindDao(CarKindDao carKindDao) {
		this.carKindDao = carKindDao;
	}

	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public void setDriverDao(DriverDao driverDao) {
		this.driverDao = driverDao;
	}

	public void testGetServiceInvalid() {
		try {
			serviceDao.get(-111L);
			fail("'badserviceid' found in the database , failing test");
		} catch (DataAccessException d) {
			assertTrue(d != null);
		}
	}

	public void testGetService() {
		Service service = serviceDao.get(-1L);

		assertNotNull(service);
		assertTrue(service.getEnabled());
		assertEquals(2, service.getDrivers().size());
		assertEquals(2, service.getLodgers().size());
		assertEquals(2, service.getPaths().size());
	}

	public void testUpdateService() {
		Service service = serviceDao.get(-1L);

		service.setServiceLocked(true);
		serviceDao.save(service);
		flush();

		service = serviceDao.get(-1L);
		assertTrue(service.getServiceLocked());

	}

	public void testAddServiceDriver() throws Exception{
		Service service = serviceDao.get(-1L);
		assertEquals(2, service.getDrivers().size());

		Driver driver = driverDao.get(-2L);
		service.addDriver(driver);
		serviceDao.save(service);
		flush();

		service = serviceDao.get(-1L);
		assertEquals(3, service.getDrivers().size());
		
		//adds same driver twice  - should the result no additional driver 
		service.addDriver(driver);
		serviceDao.save(service);
		flush();
		
		service = serviceDao.get(-1L);
		assertEquals("more that 2 drivers",3, service.getDrivers().size());
		
		endTransaction();
		
		service.getDrivers().remove(driver);
		serviceDao.save(service);
		flush();
		
		service = serviceDao.get(-1L);
		assertEquals(2, service.getDrivers().size());
	}

	public void testServiceExist(){
		assertTrue(serviceDao.exists(-1L));
	}
	
/*	public void testGetAllUnique(){
		log.debug("testing get all Unique! Services......");
		assertTrue(serviceDao.getAllServices().size() >= 3);
		log.debug(serviceDao.getAllServices().size());
	}
	public void testGetAllUniqueReserve(){
		log.debug("testing get all Unique! Reserve Services......");
		Service service = new Service();
		service.setEnabled(false);
		service.setServiceExpired(false);
		assertTrue(serviceDao.getAllServicesReadyForReserve().size() >= 0);
		log.debug(serviceDao.getAllServicesReadyForReserve().size());
	}*/
	
	public void testSearchService(){
		Service service = new Service();

		service.setTimed("8:00");
		//service.setDatebook(new Date());
//		service.setWeekday(Weekday.THURSDAY);
//		service.setEnabled(true);
		CarKind ck = carKindDao.get(-2L);
		service.setCarKind(ck); 
		
		List<Service> ss = serviceDao.searchByExample(service);
		log.debug("the size of list ="+ss.size());
		assertTrue(ss.size() >0);
		log.debug(ss.get(0).getId());
	}
	
}
