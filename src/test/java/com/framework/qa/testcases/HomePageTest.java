package com.framework.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.framework.qa.base.TestBase;
import com.framework.qa.pages.ContactPage;
import com.framework.qa.pages.HomePage;
import com.framework.qa.pages.LoginPage;
import com.framework.qa.utils.TestUtil;

public class HomePageTest extends TestBase {

	LoginPage login;
	HomePage homePage;
	TestUtil testUtil;
	ContactPage contactPage;

	public HomePageTest() {
		super();
	}
    //test cases should be independent/Separated
	//Before each test case launch the browser and login
	//After each test case close the browser
	
	@BeforeMethod
	public void setUp() {
		initialisation();
		login = new LoginPage();
		homePage= new HomePage();
		testUtil= new TestUtil();
		contactPage = new ContactPage();
		homePage=login.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title=homePage.verifyHomePageTitle();
		Assert.assertEquals(title, "CRMPRO","The Home page title is not matching... Failed!!!");
		
	}
	
	@Test(priority = 2)
	public void verifyUserNameTest(){
		testUtil.switchToframe("mainpanel");
		boolean flag = homePage.verifyUsername();
		System.out.println("The value of flag is "+flag);
		Assert.assertTrue(flag);
		
	}
	
	@Test(priority = 3)
	public void verifyContactsLinkTest() {
		testUtil.switchToframe("mainpanel");
		contactPage= homePage.clickOnContactsLink();
		
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
