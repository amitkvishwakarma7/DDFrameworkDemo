package assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Task1 {

	@Test
	public void assignment1() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver d = new ChromeDriver();
		d.manage().window().maximize();
		d.get("https://www.makemytrip.com/flights/");
		d.findElement(By.xpath("(//span[@class='lbl_input appendBottom10'])[2]")).click();
		d.findElement(By.xpath("//div[@aria-haspopup='listbox']/input")).sendKeys("Mumbai");
		List<WebElement> dests = d.findElements(By.xpath("//div[@id='react-autowhatever-1']/div/ul/li/div/div/p[1]"));

		for (WebElement dest : dests) {
			String name = dest.getText();
			System.out.println("names are : " + name);

			if (name.contains("Mumbai")) {
				dest.click();
				break;
			}
		}
//	Selecting date from from departure Tab
		d.findElement(By.xpath("//div[@aria-label='Wed May 10 2023']//p[contains(text(),'10')]")).click();
		d.findElement(By.xpath("//p[@data-cy='submit']/a")).click();
		
//	Handling pop up
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement pop = d.findElement(By.xpath("//div[@class='commonOverlay ']/span"));
		pop.click();
		
//	Selecting departure for sorting flight search
		d.findElement(By.xpath("//span[contains(text(),'Departure')]")).click();

		List<WebElement> flightDetails = d.findElements(By.xpath("//div[@class='fli-list  simpleow ']/div/div[2]/div[4]/div/div/p"));
		
		ArrayList<Integer> al = new ArrayList<>();
		
		for(WebElement flight : flightDetails) {
			
			Integer flightPrice = Integer.valueOf(flight.getText().replaceAll("[^0-9]", ""));
			
			System.out.println(flightPrice);
			al.add(flightPrice);				
			
		}
		
		Collections.sort(al);
		System.out.println(al);
		
//	Printing 2nd lowest flight price
		int secondLowestFlightPrice = al.get(1);
		System.out.println(secondLowestFlightPrice);
		
		System.out.println("The 2nd lowest flight Price: " + secondLowestFlightPrice);
		
		d.close();
		
	}
}
