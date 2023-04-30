package assignments;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.time.Duration;
import java.util.List;

import javax.imageio.ImageIO;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import java.awt.datatransfer.StringSelection;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Task2 {
	WebDriver d; 
	Actions act;
	WebDriverWait wait;
	
	@BeforeTest
	public void launchBrowser() {
		d = new ChromeDriver();
		d.manage().window().maximize();
		d.get("https://mail.aol.com");
		
	}
	@AfterTest
	public void tearDown() throws InterruptedException {
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		d.close();

	}
	@BeforeClass
	public void login() {
		
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement loginPage = d.findElement(By.xpath("//a[@class='login']"));
		loginPage.click();
		WebElement usenameTextBox = d.findElement(By.id("login-username"));
		usenameTextBox.sendKeys("a.kumar80");
		WebElement nextButton1 = d.findElement(By.id("login-signin"));
		nextButton1.click();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement passwordTextBox = d.findElement(By.id("login-passwd"));
		passwordTextBox.sendKeys("Tester@1234");
		WebElement submitButton = d.findElement(By.id("login-signin"));
		submitButton.click();	
	}
	@AfterClass
	public void logout() throws InterruptedException {
		
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement userMenu = d.findElement(By.xpath("//span[@role='presentation']"));
		userMenu.click();
		WebElement signOutBtn = d.findElement(By.xpath("//span[@class='_yb_yzvf4 _yb_imajb _yb_i9kou']"));
		signOutBtn.click();
		
	}	
	@Test
	public void task2() throws InterruptedException, AWTException, IOException {
		
		String expectedImageSize = "3.7kB";
		String expectedSubject = "Damco";
		
		wait = new WebDriverWait(d, Duration.ofSeconds(30));
		
//	Had to use Thread.sleep instead of selenium waits for synchronization otherwise its throwing exception
		Thread.sleep(3000);
		
//	clicking on inbox mail tab
		
		WebElement inboxTab = d.findElement(By.xpath("(//ul[@class='hd_n P_0 M_0'])[1]/li[1]/div/a"));

		inboxTab.click();
		
//	Checking the count of email in inbox before sending email
		int inboxEmailExpectedCount = d.findElements(By.xpath("//div[@data-test-id='virtual-list']/ul/li")).size();
		
		System.out.println("Email count before drafting email in inbox : "+inboxEmailExpectedCount);
		
//	Clicking on compose button for new email	
		
		WebElement composeEmailButton = d.findElement(By.xpath("//div[@data-test-id='navigation']/div/a")); 
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		composeEmailButton.click();
		
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		WebElement sendToInbox = d.findElement(By.id("message-to-field"));
		sendToInbox.sendKeys("a.kumar80@aol.com");
		WebElement subjectTexBox = d.findElement(By.xpath("//input[@data-test-id='compose-subject']"));
		subjectTexBox.sendKeys("Damco");
		WebElement bulletListBtn = d.findElement(By.xpath("//button[@title='Bulleted List']//*[name()='svg']"));
		bulletListBtn.click();
		
		act = new Actions(d);
		WebElement bulletPoint = d.findElement(By.xpath("(//span[@class='D_F ab_C gl_C W_6D6F'])[18]"));
		bulletPoint.click();
		WebElement emailBodyLine1 = d.findElement(By.xpath("//div[@data-test-id='compose-editor-container']/div/div[2]/div/div/div/ul/li"));
		emailBodyLine1.sendKeys("Line one");
		act.sendKeys(Keys.ENTER).perform();
		WebElement emailBodyLine2 = d.findElement(By.xpath("//div[@data-test-id='compose-editor-container']/div/div[2]/div/div/div/ul/li[2]"));
		emailBodyLine2.sendKeys("Line two");
		act.sendKeys(Keys.ENTER).perform();
		WebElement emailBodyLine3 = d.findElement(By.xpath("//div[@data-test-id='compose-editor-container']/div/div[2]/div/div/div/ul/li[3]"));
		emailBodyLine3.sendKeys("Line three");
		WebElement attacheButton = d.findElement(By.xpath("//span[contains(text(),'Attach')]"));
		attacheButton.click();
		
//	Using Robot class to attach the file in email draft
		
		Robot rb = new Robot();
		rb.delay(2000);
		StringSelection ss = new StringSelection("D:\\ScreenShot_folder\\screenshot.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		
		rb.keyPress(KeyEvent.VK_ENTER);
		
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		
//	Clicking on the send button
		WebElement sendEmailBtn = d.findElement(By.xpath("//button[@title='Send this email']//span"));
		sendEmailBtn.click();
		
//	Checking the count of email received in inbox tab
		
		Thread.sleep(3000);
//	Had to use Thread.sleep instead of selenium waits for synchronization otherwise its throwing exception
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		inboxTab.click();
		
//	Had to use Thread.sleep instead of selenium waits for synchronization otherwise its throwing exception
		Thread.sleep(9000);
		
		int inboxActualCount = d.findElements(By.xpath("//div[@data-test-id='virtual-list']/ul/li")).size();
		System.out.println("Email count after sending email : "+inboxActualCount);
		
		if(inboxActualCount>inboxEmailExpectedCount) {
						
			System.out.println("Email has been received in the inbox");
			
//		clicking on latest email for verification
			d.findElement(By.xpath("//div[@data-test-id='virtual-list']/ul/li[3]")).click();
			
//		Verifying email body
			WebElement emailBody1= d.findElement(By.xpath("//div[@data-test-id='message-view-body-content']/div/div/div/div/div/div/ul/li[1]"));
			WebElement emailBody2= d.findElement(By.xpath("//div[@data-test-id='message-view-body-content']/div/div/div/div/div/div/ul/li[2]"));
			WebElement emailBody3= d.findElement(By.xpath("//div[@data-test-id='message-view-body-content']/div/div/div/div/div/div/ul/li[3]"));
			
			String actualText1 = emailBody1.getText();
			String actualText2 = emailBody2.getText();
			String actualText3 = emailBody3.getText();
			
			System.out.println("Actual email body Line one text : "+actualText1);
			System.out.println("Actual Email body Line two text : "+actualText2);
			System.out.println("Actual Email body Line three text : "+actualText3);
			
			Assert.assertEquals(actualText1, "Line one");
			Assert.assertEquals(actualText2, "Line two");
			Assert.assertEquals(actualText3, "Line three");
					
//		Verifying Subject line
			
			String actualSubject = d.findElement(By.xpath("//span[@data-test-id='message-group-subject']")).getText();
			Assert.assertEquals(actualSubject, expectedSubject);
			System.out.println("Actual Subject line test : "+actualSubject);
		
//		Image downloading
			WebElement imageDownload = d.findElement(By.xpath("//div[@class='u_dzD A_6DUj']//parent::span/parent::a/parent::div/parent::div/preceding-sibling::div/div[1]"));
			Thread.sleep(4000);
//		Had to use Thread.sleep() instead of selenium waits for synchronization otherwise it thows exception
			imageDownload.click();
			boolean actualStatus = imageDownload.isEnabled();
			System.out.println("Image display status is : "+actualStatus);
			
//		Verifying image size
			
			String actualImageSize = d.findElement(By.xpath("//a[@data-test-id='attachment-download']/span/div[2]")).getText();
			System.out.println("Actual image size : "+actualImageSize);
			Assert.assertEquals(actualImageSize, expectedImageSize);			
					
			
		}
		else {
			System.out.println("Email has not been received in the inbox");
		}
		
		
		
		
	}

}
