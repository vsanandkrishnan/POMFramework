package com.framework.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.framework.qa.base.TestBase;
import com.framework.qa.pages.HomePage;
import com.framework.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage login;
	HomePage homePage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		// System.out.println("The current thread ID is " +
		// Thread.currentThread().getId());
		initialisation();
		login = new LoginPage();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = login.validateLoginPageTitle();
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");

	}

	@Test(priority = 2)
	public void crmLogoTest() {
		boolean flag = login.validateCRMImage();
		Assert.assertTrue(flag);

	}

	@Test(priority = 3)
	public void loginTest() {
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		homePage = login.login(username, password);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
