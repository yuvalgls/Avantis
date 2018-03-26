package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Tools.Time;
import selenium.Selenium;

public class JoinUsPage extends BasePage {
	
	@FindBy(xpath = "//a[text()='OPEN POSITIONS ']")
	private WebElement btnViewOpenPositions;
	
	@FindBy(xpath = "//div[@class='positionContainerMain']//a[text()='QA Automation 1257']")
	private WebElement btnPosition;
	
	
	
	public JoinUsPage(Selenium selenium) {
		super(selenium);
	}
	
	
	public void clickOnViewOpenPositions(){
		selenium.click(btnViewOpenPositions);
		Time.sleep();
	}
	
	public void clickOnPosition(){
		selenium.click(btnPosition);
		Time.sleep();
	}

}
