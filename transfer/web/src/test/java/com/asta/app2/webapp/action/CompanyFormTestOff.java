package com.asta.app2.webapp.action;

import com.asta.app2.model.Company;
import com.asta.app2.service.CityManager;
import com.asta.app2.service.CompanyManager;
import com.asta.app2.util.BundleUtil;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class CompanyFormTestOff extends BasePageTestCase {
	private CompanyForm bean;
	private CompanyManager companyManager;
	private CityManager cityManager;

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}

	@Override
	protected void onSetUp() throws Exception {
		super.onSetUp();
		bean = new CompanyForm();
		bean.setCompanyManager(companyManager);
		bean.setCityManager(cityManager);
	}

	@Override
	protected void onTearDown() throws Exception {
		super.onTearDown();
		bean = null;
	}

/*	public void testAdd() throws Exception {
		Company company = new Company();

		// enter all required fields
		company.setName("FuWuYeDfJzEtVhOfRaOuOfPjUo");
		company.setCode("" + Math.random());
		bean.setCityID("-1");
		company.setCity(cityManager.get(Long.valueOf(bean.getCityID())
				.longValue()));
		bean.setCompany(company);

		assertEquals("list", bean.save());
		assertFalse(bean.hasErrors());
	}

	public void testEdit() throws Exception {
		log.debug("testing edit...");
		bean.setId(-1L);

		assertEquals("edit", bean.edit());
		assertNotNull(bean.getCompany());
		assertFalse(bean.hasErrors());
	}

	public void testSave() {
		log.debug("testing save...");
		bean.setId(-1L);

		assertEquals("edit", bean.edit());
		assertNotNull(bean.getCompany());
		Company company = bean.getCompany();

		// update required fields
		company.setName("IbFhNxOsUqQgZdNpHfOaQz");
		company.setCode("LkOdMgMnXq");
		bean.setCompany(company);

		assertEquals("edit", bean.save());
		assertFalse(bean.hasErrors());
	}*/

	public void testRemove() throws Exception {
		log.debug("testing remove...");
		Company company = new Company();
		company.setId(-2L);
		bean.setCompany(company);

		assertEquals("list", bean.delete());
		assertFalse(bean.hasErrors());
	}

	public void testFindByCode() throws Exception {
		log.debug("testing findByCode......");
		assertTrue(companyManager
				.findByCode(
						BundleUtil
								.getMessageBundle("default.servicetemplate.company.code"))
				.size() == 1);
	}
}