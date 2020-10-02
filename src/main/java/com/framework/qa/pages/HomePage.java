package com.framework.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.framework.qa.base.TestBase;

public class HomePage extends TestBase {
    
	@FindBy(xpath = "//td[contains(text(),'Demo')]")
	@CacheLookup
	WebElement userLabel;

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContactsLink;

	public HomePage() {

		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		String title = driver.getTitle();
		return title;
	}

	public ContactPage clickOnContactsLink() {
		contactsLink.click();
		return (new ContactPage());

	}

	public DealsPage clickOnDealsLink() {
		
		dealsLink.click();
		return new DealsPage();
	}

	public TaskPage clickOnTasksLink() {
		tasksLink.click();
		return new TaskPage();

	}
	public boolean verifyUsername() {
		return userLabel.isDisplayed();
	}
	
	public void clickOnNewContactLink() {
		Actions action = new Actions(driver);

		
//		/((JavascriptExecutor) driver).executeScript("arguments[0].focus();",contactsLink);
		action.moveToElement(contactsLink).build().perform();

		newContactsLink.click();
		
	}

}
