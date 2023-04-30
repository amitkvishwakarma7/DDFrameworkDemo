package testsClasses;

import baseClass.BaseClass;
import pageobjects.AccountPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.DataUtil;
import util.MyXLSReader;

public class LoginTest extends BaseClass {

	WebDriver d;
	MyXLSReader excelSheet;

	@AfterMethod
	public void tearDown() {
		d.close();
	}

	@Test(dataProvider = "dataSupplier")
	public void login(HashMap<String, String> hMap) {

		if (!DataUtil.isRunnable(excelSheet, "LoginTest", "Testcases") || hMap.get("Runmode").equals("N")) {
			throw new SkipException("The runmode is set to N, hence not executed");
		}

		d = openBrowser(hMap.get("Browser"));

		HomePage homePage = new HomePage(d);
		homePage.clickOnMyAccount();
		LoginPage loginPage = homePage.clickOnLoginOption();
		loginPage.enterEmailAddress(hMap.get("Username"));
		loginPage.enterPassword(hMap.get("Password"));
		AccountPage acPage = loginPage.clickOnLoginButton();

		String expectedResult = hMap.get("ExpectedResult");
		
		boolean expectedConvertedResult = false;
		
		if (expectedResult.equalsIgnoreCase("Success")) {
		
			expectedConvertedResult = true;
		} 
		else if (expectedResult.equalsIgnoreCase("Failure")) {
		
			expectedConvertedResult = false;
		}
		
		boolean actualResult = false;

		actualResult = acPage.verifyDisplayStatus();
		
		Assert.assertEquals(actualResult, expectedConvertedResult);

	}

	@DataProvider
	public Object[][] dataSupplier() {

		Object[][] data = null;
		try {
			excelSheet = new MyXLSReader("src\\test\\resources\\DemoTestData.xlsx");
			data = DataUtil.getTestData(excelSheet, "LoginTest", "Data");

		} catch (Throwable e) {
			e.printStackTrace();
		}

		return data;

	}

}
