package com.framework.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.framework.qa.base.TestBase;

import io.qameta.allure.Step;

public class LoginPage extends TestBase {
	
	//Page Factory or object repository
	
	@FindBy(name ="username")
	WebElement username;
	
	@FindBy(name ="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginButton;
	
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")
	WebElement signUpButton;
	
	@FindBy(css=".navbar-brand")
	WebElement crmLogo;
	
	//Initializing page Objects  
	public LoginPage() {
		PageFactory.initElements(driver,this);
	}
	
	//Different actions
	@Step("getting login page title")
	public String validateLoginPageTitle() {		
		return driver.getTitle();	
	}
	
	@Step("Verify the crmLogo after login")
	public boolean validateCRMImage() {		
		boolean isDisplayed= crmLogo.isDisplayed();
		return isDisplayed;		
	}
	
	@Step("Login with username:{0} and password:{1}")
	public HomePage login(String username,String password) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		loginButton.click();
		return (new HomePage());
	}
	
	
	
	

}
