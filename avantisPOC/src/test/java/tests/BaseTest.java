package tests;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.xml.XmlTest;

import selenium.Selenium;

public class BaseTest {
	/*
	 * this class is the base class of the test it can use to start the driver,
	 * and set up the env ext...
	 */
	private static Map<String, Object> TESTS_OBJ = new HashMap<String, Object>();

	@BeforeMethod
	public void startSeleniumDriver(Method method, XmlTest xml) {
		Assert.assertNotNull(xml);
		Selenium selenium = new Selenium(xml.getParameter("browser"), method);
		System.out.println("Testing " + method.getDeclaringClass().getName() + "." + method.getName());
		setSeleniumByMethodName(method, selenium);
	}

	@AfterMethod
	public void killSeleniumDriver(Method method, ITestResult result) {
		Selenium selenium = getSeleniumByMethodName(method);
		
		//in case the test failed take a screenshot
		if(selenium != null)
			if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP)
				 selenium.takeScreenShot();
			
		selenium.killDriver();
	}

	
	
	/*######################################### Private Methods #########################################*/
	
	private static void setSeleniumByMethodName(Method method, Selenium selenium) {
		Assert.assertNotNull(selenium, "selenium driver cannot be set as null");
		String methodName = (method.getDeclaringClass().getName() + "." + method.getName());
		System.out.println("Setting selenium object for " + methodName);
		TESTS_OBJ.put(methodName + "_selenium", selenium);
	}
	
	protected static Selenium getSeleniumByMethodName(Method method){
		String methodName = (method.getDeclaringClass().getName() + "." + method.getName());
		Selenium selenium = null;
		
		if(TESTS_OBJ.get(methodName + "_selenium") != null)
			selenium = (Selenium) TESTS_OBJ.get(methodName + "_selenium");
		
		if(selenium == null)
			System.out.println("Failed to get Selenium object for test method " + methodName + ", cached objects: " + TESTS_OBJ);
		
		return selenium;
	}
}
