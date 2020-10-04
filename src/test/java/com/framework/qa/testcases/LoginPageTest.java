package com.framework.qa.testcases;



import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.framework.qa.base.TestBase;
import com.framework.qa.pages.HomePage;
import com.framework.qa.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LoginPageTest extends TestBase {

	LoginPage login;
	HomePage homePage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		System.out.println("The current thread ID is  "+Thread.currentThread().getId());
		initialisation();
		login = new LoginPage();
	}

	@Test(priority = 1,description = "Verify Login page title test.")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify Login Page Title Test on login page.")
	@Story("Story Name: To Check login page title.")
	public void loginPageTitleTest() {
		String title = login.validateLoginPageTitle();
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");

	}

	@Test(priority = 2,description="verify the signup link")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Verify Sign Up link  on login page.")
	@Story("Story Name: To Check signUp link")
	public void crmLogoTest() {
		boolean flag = login.validateCRMImage();
		Assert.assertTrue(flag);

	}

	@Test(priority = 3,description = "Login into app test")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify the login into application with correct credentials")
	@Story("Story Name: To check Login functionality")
	public void loginTest() {
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
	 homePage=	login.login(username, password);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
