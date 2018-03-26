package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import selenium.Selenium;

public class ContectUsPage extends BasePage {
	@FindBy(id = "contact")
	private WebElement viewContace;
	
	@FindBy(xpath = "//input[@name='your-name']/../span")
	private WebElement lblNameMendetoryFieldErrorMsg;
	
	@FindBy(xpath = "//input[@name='your-email']/../span")
	private WebElement lblEmailMendetoryFieldErrorMsg;
	
	@FindBy(xpath = "//div[@style='display: block;']")
	private WebElement lblInvalidFormErrorMsg;
	
	@FindBy(xpath = "//input[@type='submit' and @value='CONTACT US']")
	private WebElement btnSubmitForm;

	public ContectUsPage(Selenium selenium) {
		super(selenium);
		validetePage();
	}

	private void validetePage() {
		selenium.validateElementDisplay(viewContace);
	}
	
	public void clickOnSubmitForm(){
		selenium.waitForElementBeVisible(btnSubmitForm);
		selenium.click(btnSubmitForm);
	}
	
	public void validateMendetoryFieldsComment(){
		selenium.waitForElementPresent(lblNameMendetoryFieldErrorMsg);
		selenium.validateElementDisplay(lblNameMendetoryFieldErrorMsg);
		selenium.validateElementDisplay(lblEmailMendetoryFieldErrorMsg);
		selenium.validateElementDisplay(lblInvalidFormErrorMsg);
	}
}
