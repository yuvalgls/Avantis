package pages;

import java.io.File;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import selenium.Selenium;

public class PositionPage extends BasePage {
	@FindBy(xpath = "//input[@type='file']")
	private WebElement fileResumeUpload;
	
	@FindBy(xpath = "//input[@placeholder='Full Name']")
	private WebElement txtApplacationFullName;
	
	@FindBy(xpath = "//input[@placeholder='Email']")
	private WebElement txtApplacationEmail;
	
	@FindBy(xpath = "//input[@placeholder='Phone']")
	private WebElement txtApplacationPhone;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement btnSubmitApplication;
	
	@FindBy(xpath = "//*[text()='Please correct errors.']")
	private WebElement lblApplicationWrrorMsg;
	
	public PositionPage(Selenium selenium) {
		super(selenium);
	}

	public void clickOnSubmitApplication(){
		selenium.click(btnSubmitApplication);
	}
	
	public void fillForm(){
		enterApplicationFullName();
		enterApplicationEmail();
		enterApplicationPhoneNum();
	}
	
	public void enterApplicationFullName(){
		selenium.sendKeys(txtApplacationFullName, "Yuval Glasman");
	}
	
	public void enterApplicationEmail(){
		selenium.sendKeys(txtApplacationEmail, "yuval.glasman@gmail.com");
	}
	
	public void enterApplicationPhoneNum(){
		selenium.sendKeys(txtApplacationPhone, "0507467698");
	}
	
	public void uploadResume(){
		selenium.sendKeys(fileResumeUpload, new File("/src/test/resources/YuvalGlasman.docx").getAbsolutePath());
	}
	
	public void validateApplicationErrorMsgIsShow(){
		selenium.waitForElementPresent(lblApplicationWrrorMsg);
		selenium.validateElementDisplay(lblApplicationWrrorMsg);
	}

	public void validateFieldsAreAllign() {
		Assert.assertEquals(selenium.getElementXPosition(btnSubmitApplication), selenium.getElementXPosition(fileResumeUpload), "File upload and Submit Buttons are not align");
	}
}
