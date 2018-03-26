package selenium;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;

import Tools.Time;

/*this is the main selenium class, it can load the driver on a remove server or local machine according to the constructor*/
public class Selenium {
	private WebDriver driver = null;
	private Method method = null;
	private int defaultWaitTime = 300;

	public Selenium(String browser, Method method) {
		this.method = method;
		Assert.assertNotNull(browser);
		driverSelector(browser);
	}
	
	private void driverSelector(String browser) {
		Reporter.log("Starting " + browser);
		switch(browser){
		case"chrome":
			loadChrome();
			break;
		case"firefox":
			loadFirefox();
			break;
		case"internet explorer":
		case"ie":
			loadInternetExplorer();
			break;
		default:
			Assert.fail(browser + " isn't supported, please contect Yuval Glasman");
			break;
		}		
	}

	private void loadFirefox() {
		throw new SkipException("FF not yet implimented");
	}

	private void loadInternetExplorer() {
		throw new SkipException("IE not yet implimented");
	}

	public void loadChrome() {
		Reporter.log("Loading Chrome");
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver();
		setupDriver();
	}

	public void killDriver() {
		Reporter.log("Closing browser");
		try{
			driver.quit();
		}catch(Exception e){
		}catch (Throwable e) {
		}
	}

	public void setupDriver(){
		setupDriver(defaultWaitTime, defaultWaitTime, defaultWaitTime);
		load("http://www.avantisteam.com");
	}
	
	public void setupDriver(int pageLoadTimeout, int implicitlyWait, int scriptTimeout) {
		this.driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
		this.driver.manage().timeouts().setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();
	}
	
	public void load(String url) {
		try {
			Reporter.log("Loading " + url);
			this.driver.get(url);
		} catch (Exception e) {
			if (this.driver != null) 
				killDriver();
				
			Assert.fail("Fail to load URL " + url + ", error: " + e.getLocalizedMessage());
		}
	}
	
	/*
	 * this mathod is use to type to a text field
	 * */
	public void sendKeys(WebElement element, String text) {
		try {
			Reporter.log("Typing " + text + " to " + element.getTagName());
			waitForElementPresent(element);
			try {
				element.clear();
			} catch (Exception e) {
			}

			element.sendKeys(text);
			Time.sleep();
		} catch (Exception e) {
			Reporter.log("Failed to type " + text + " error: " + e.getLocalizedMessage());
		}
	}
	
	/*
	 * this mathod is use to click on a button
	 * */
	public void click(WebElement element) {
		Assert.assertNotNull(element, "unable to click on null element");
		
		waitForElementToBeClickable(element);
		String txt = getElementText(element); 
		if (txt != null && txt.length() > 0)
			Reporter.log("Clicking on \"" + txt + "\""); 

		// click
		try {
			element.click();
		} catch (Exception e) {
			Reporter.log("Error while clicking on element, error: " + e.getLocalizedMessage());
		}
		Time.sleep();
	}
	
	/*
	 * this mathod is used to take screenshot of the page in case of a failed test
	 * */
	public void takeScreenShot() {
		File file = new File("target/surefire-reports/screenshot/" + method.getDeclaringClass().getName() + "." + method.getName() + ".jpg");
		try {
			Reporter.log("Saving screenshot to " + file.getAbsolutePath() );
			File scrFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, file);
		} catch (Exception e) {
			Reporter.log("Failed to take screenshot " + e.getMessage());
		}
	}
	
	public String getElementText(WebElement element) {
		Assert.assertNotNull(element);
		waitForElementPresent(element);
		String txt = null;
		try {
			txt = element.getText();
		} catch (Exception e) {
			Reporter.log("failed to get element text , error: " + e.getLocalizedMessage());
		}
		return txt;
	}
	
	public void waitForElementPresent(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(this.driver, defaultWaitTime);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			Reporter.log("timeout while waiting for element");
		}
	}
	
	public void waitForElementToBeClickable(WebElement element) {
		try{
			WebDriverWait wait = new WebDriverWait(this.driver, defaultWaitTime);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}catch(Exception e){
			Reporter.log("timeout while waiting for element to be available");
		}
	}
	
	public void validateElementDisplay(WebElement element) {
		Assert.assertTrue(isElementDisplay(element), "Element isn't displayed");
	}

	public Boolean isElementDisplay(WebElement element) {
		turnOffImplicitWaits();
		Boolean displayed = false;
		try {
			if(element != null)
				displayed = element.isDisplayed();
		} catch (Exception e) {
			displayed = false;
		}
		turnOnImplicitWaits();
		return displayed;
	}
	
	public void turnOffImplicitWaits() {
	    setupDriver(1, 1, 1);
	}

	public void turnOnImplicitWaits() {
	    setupDriver(defaultWaitTime ,defaultWaitTime ,defaultWaitTime);
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public void switchToNewTab() {
		Reporter.log("Switching to the first tab");
		ArrayList<String> tabs = new ArrayList<String>(this.driver.getWindowHandles());
		Assert.assertEquals(tabs.size(), 2);
		this.driver.switchTo().window(tabs.get(1));
	}

	public void closeTab() {
		Reporter.log("Closing tab");
		this.driver.close();
	}

	public void returnToMainTab() {
		Reporter.log("Returning to the main tab");
		ArrayList<String> tabs = new ArrayList<String>(this.driver.getWindowHandles());
		this.driver.switchTo().window(tabs.get(0));
	}
	
	public int getElementXPosition(WebElement element){
		return element.getLocation().getX();
	}

	public int getElementYPosition(WebElement element){
		return element.getLocation().getY();
	}
	
	public void waitForElementBeVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(this.driver, defaultWaitTime);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
		}
	}
}
