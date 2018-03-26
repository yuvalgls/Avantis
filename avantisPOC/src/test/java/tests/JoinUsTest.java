package tests;

import org.testng.annotations.Test;

import pages.HomePage;
import pages.JoinUsPage;
import pages.PositionPage;
import pages.TeamPage;
import selenium.Selenium;

public class JoinUsTest extends BaseTest {
	/*
	 * validate the submit form flow, 
	 * 
	 * */
	
	//TODO: validates resume file arrives to server
	//TODO: add test to validate file type upload
  @Test
  public void submitApplication() {
		Selenium selenium =  getSeleniumByMethodName(new Object(){}.getClass().getEnclosingMethod());
		
		HomePage hpPageObj = new HomePage(selenium);
		hpPageObj.clickOnTeam();
		
		TeamPage teamPageObj = new TeamPage(selenium);
		teamPageObj.clickOnJoinUs();
		
		JoinUsPage joinUsPageObj = new JoinUsPage(selenium);
		joinUsPageObj.clickOnViewOpenPositions();
		joinUsPageObj.clickOnPosition();

		PositionPage positionPageObj = new PositionPage(selenium);
		positionPageObj.fillForm();
		
		/*in a ream test i would continue to upload my resume...*/ 
  }
  
  /*
	 * validate mendetory fields messages on the join us application form, 
	 * 
	 * */
  @Test
  public void validateMendetoryFields() {
		Selenium selenium =  getSeleniumByMethodName(new Object(){}.getClass().getEnclosingMethod());
		
		HomePage hpPageObj = new HomePage(selenium);
		hpPageObj.clickOnTeam();
		
		TeamPage teamPageObj = new TeamPage(selenium);
		teamPageObj.clickOnJoinUs();
		
		JoinUsPage joinUsPageObj = new JoinUsPage(selenium);
		joinUsPageObj.clickOnPosition();
		
		PositionPage positionPageObj = new PositionPage(selenium);
		positionPageObj.clickOnSubmitApplication();
		positionPageObj.validateApplicationErrorMsgIsShow();
  }
  
  /*
	 * validate buttons are align 
	 * 
	 * */
  @Test
  public void validateFieldsAllignment() {
		Selenium selenium =  getSeleniumByMethodName(new Object(){}.getClass().getEnclosingMethod());
		
		HomePage hpPageObj = new HomePage(selenium);
		hpPageObj.clickOnTeam();
		
		TeamPage teamPageObj = new TeamPage(selenium);
		teamPageObj.clickOnJoinUs();
		
		JoinUsPage joinUsPageObj = new JoinUsPage(selenium);
		joinUsPageObj.clickOnPosition();
		
		PositionPage positionPageObj = new PositionPage(selenium);
		positionPageObj.validateFieldsAreAllign();
  }
}
