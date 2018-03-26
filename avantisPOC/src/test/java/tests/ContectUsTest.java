package tests;

import org.testng.annotations.Test;

import pages.ContectUsPage;
import pages.HomePage;
import selenium.Selenium;

public class ContectUsTest extends BaseTest {
	
	/*
	 * test to validate mendetory fields messages on the contect up form
	 * */
	@Test
	public void mendetoryFieldsTest() {
		Selenium selenium =  getSeleniumByMethodName(new Object(){}.getClass().getEnclosingMethod());
		
		HomePage hpPageObj = new HomePage(selenium);
		hpPageObj.clickOnContect();
		
		ContectUsPage contectUsPageObj = new ContectUsPage(selenium);
		contectUsPageObj.clickOnSubmitForm();
		contectUsPageObj.validateMendetoryFieldsComment();
	}
	
	
	//TODO: add a submit form test
	
}
