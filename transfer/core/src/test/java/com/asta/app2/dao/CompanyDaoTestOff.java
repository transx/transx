/* *Class created on [ Jun 1, 2008 | 9:53:11 AM ] */
package com.asta.app2.dao;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CompanyDaoTestOff extends BaseDaoTestCase {
	private CompanyDao companyDao = null;
	
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public void testFindByCode() throws Exception {
		log.debug("testing findByCode......");
		assertTrue(companyDao.findByCode("11222").size() ==1);
	}

}
