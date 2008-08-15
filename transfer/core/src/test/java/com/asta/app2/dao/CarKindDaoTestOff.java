/* *Class created on [ Jun 1, 2008 | 6:41:14 PM ]*/ 
package com.asta.app2.dao;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CarKindDaoTestOff extends BaseDaoTestCase {
	private CarKindDao carKindDao = null;

	public void setCarKindDao(CarKindDao carKindDao) {
		this.carKindDao = carKindDao;
	}

	public void testFindByName() throws Exception {
		log.debug("testing findByName......");
		assertTrue(carKindDao.findByName("ولوو40").size() == 1);
	}

}
