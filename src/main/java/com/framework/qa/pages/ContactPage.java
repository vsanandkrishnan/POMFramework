package com.framework.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.framework.qa.base.TestBase;

public class ContactPage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;

	@FindBy(xpath = "//select[@name='title']")
	WebElement titleLink;

	@FindBy(id = "first_name")
	WebElement firstNameId;

	@FindBy(id = "surname")
	WebElement secondNameId;

	@FindBy(xpath = "//input[@name='client_lookup']")
	WebElement companyNameXpath;
	
	@FindBy(xpath="//input[@value='Save']")
	WebElement contactsPageSave;

//	@FindBy(xpath="//td//a[contains(text(),'Aaa Chhii')]/../../td[1]")
//	WebElement checkBox;

	public ContactPage() {
		PageFactory.initElements(driver, this);

	}

	public boolean verifycontactsLabel() {
		return contactsLabel.isDisplayed();
	}

	public void selectContacts(String contactName) {
		String xpathContactName = "//td//a[contains(text(),'" + contactName + "')]/../../td[1]";
		driver.findElement(By.xpath(xpathContactName)).click();
	}

	public void createNewContact(String title, String firstName, String lastName, String companyName) {
		Select sel = new Select(titleLink);
		sel.selectByVisibleText(title);
		
		//enter the data into the new contact page
		firstNameId.sendKeys(firstName);
		secondNameId.sendKeys(lastName);
		companyNameXpath.sendKeys(companyName);
		
		contactsPageSave.click();

	}
}
