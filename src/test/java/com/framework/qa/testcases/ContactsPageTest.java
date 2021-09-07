package com.framework.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.qa.base.TestBase;
import com.framework.qa.pages.ContactPage;
import com.framework.qa.pages.HomePage;
import com.framework.qa.pages.LoginPage;
import com.framework.qa.utils.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactPage contactPage;
	
	String sheetName="contacts";
	
	
	public ContactsPageTest() {
		super();
		
	}
	
	@BeforeMethod
	public void setUp() {
		initialisation();
		loginPage = new LoginPage();
		//homePage = new HomePage();
		testUtil = new TestUtil();
		contactPage= new ContactPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToframe("mainpanel");
		contactPage=homePage.clickOnContactsLink();
		
	}
	
	
	@Test(priority = 1,enabled = true)
	public void verifyContactsLabelTest() {
		boolean flag=contactPage.verifycontactsLabel();
		Assert.assertTrue(flag,"Contact Label is missing on the contact page!!! FAILED!!");
		
	}
	
	@Test(priority = 2,enabled = true)
	public void selectContactByNameTest() {
		contactPage.selectContacts("Aaa Chhii");
		
		
	}
	
	@Test(priority = 3,enabled = true)
	public void selectMultipleContactsByNameTest() {
		contactPage.selectContacts("Aaa Chhii");
		contactPage.selectContacts("Aaa Handwash");
	}
	
	@Test(priority=4,dataProvider = "getTestData",enabled=false)
	public void validateCreateNewContactTest(String title,String firstName,String lastName,String company) {
		homePage.clickOnNewContactLink();
		contactPage.createNewContact(title, firstName, lastName, company);
		
	}
	
	@DataProvider
	public Object[][]  getTestData() {
		Object[][] data= testUtil.getTestData(sheetName);
		
		
		return data;
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}

}
