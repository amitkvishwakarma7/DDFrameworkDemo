package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	WebDriver driver;

	public AccountPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Edit your account information")
	private WebElement accountInformationOption;

	public boolean verifyDisplayStatus() {
		
		boolean displayStatus = false;

		try {
			displayStatus=  accountInformationOption.isDisplayed();

		} catch (Throwable e) {
			
			displayStatus = false;
		}
		return displayStatus;
	}

}
