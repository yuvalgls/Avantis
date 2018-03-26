package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import selenium.Selenium;

public class TeamPage extends BasePage {
	@FindBy(id = "team")
	private WebElement viewTeam;
	
	@FindBy(xpath = "//a[contains(@class, 'YellowJoinUs')]")
	private WebElement btnJoinUs;
	
	public TeamPage(Selenium selenium) {
		super(selenium);
		validetePage();
	}

	private void validetePage() {
		selenium.validateElementDisplay(viewTeam);
	}
	
	public void clickOnJoinUs(){
		selenium.waitForElementBeVisible(btnJoinUs);
		selenium.click(btnJoinUs);
		selenium.switchToNewTab();
	}
}
