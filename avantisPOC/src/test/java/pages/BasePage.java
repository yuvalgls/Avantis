package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.Selenium;

public class BasePage {
	protected Selenium selenium = null;

	@FindBy(id = "header_logo")
	private WebElement pibHeaderLogo;
	
	@FindBy(xpath = "//ul[@id='menu-top-menu']/li/a[text()='ABOUT']")
	private WebElement btnHeaderAbout;
	
	@FindBy(xpath = "//ul[@id='menu-top-menu']/li/a[text()='VISION']")
	private WebElement btnHeaderVision;
	
	@FindBy(xpath = "//ul[@id='menu-top-menu']/li/a[text()='AWESOMENESS']")
	private WebElement btnHeaderAwesomeness;
	
	@FindBy(xpath = "//ul[@id='menu-top-menu']/li/a[text()='COOL FACTS']")
	private WebElement btnHeaderCoolFacts;
	
	@FindBy(xpath = "//ul[@id='menu-top-menu']/li/a[text()='TEAM']")
	private WebElement btnHeaderTeam;
	
	@FindBy(xpath = "//ul[@id='menu-top-menu']/li/a[text()='INNOVATE']")
	private WebElement btnHeaderInnovate;
	
	@FindBy(xpath = "//ul[@id='menu-top-menu']/li/a[text()='SOCIALIZE']")
	private WebElement btnHeaderSocialize;
	
	@FindBy(xpath = "//ul[@id='menu-top-menu']/li/a[text()='CONTACT']")
	private WebElement btnHeaderContact;
	
	@FindBy(xpath = "//a[contains(@class, 'email')]")
	private WebElement btnShareViaEmail;
	
	@FindBy(id = "menuResponsive")
	private WebElement menuResponsive;
	
	public BasePage(Selenium selenium){
		this.selenium = selenium;
		PageFactory.initElements(selenium.getDriver(), this);
	}

	public void clickOnContect() {
		if(selenium.isElementDisplay(menuResponsive))
			selenium.click(menuResponsive);
					
		selenium.click(btnHeaderContact);
	}
	
	public void clickOnTeam() {
		if(selenium.isElementDisplay(menuResponsive))
			selenium.click(menuResponsive);
		
		selenium.click(btnHeaderTeam);
	}
	
	public void clickOnShareViaEmail(){
		selenium.click(btnShareViaEmail);
	}

}
