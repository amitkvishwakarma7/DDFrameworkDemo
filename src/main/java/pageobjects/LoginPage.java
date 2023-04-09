package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver,this);
	}

	@FindBy(id="input-email")private WebElement emailIdField;
	@FindBy(id="input-password")private WebElement passwordField;
	@FindBy(xpath="//input[@class='btn btn-primary']")private WebElement loginButton;
	
	
	public void enterEmailAddress(String emailId) {
		emailIdField.sendKeys(emailId);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		
		return new AccountPage(driver);
	}
	
	
}
