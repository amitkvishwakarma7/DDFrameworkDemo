package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public Properties prop;
	
	public WebDriver openBrowser(String browser) {
		WebDriver d = null;
		
		prop = new Properties();
		File propFile = new File("src\\test\\resources\\data.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}
		catch(Throwable e) {
			e.printStackTrace();
		}
		
		if (browser.equalsIgnoreCase("chrome")) {
//			WebDriverManager.chromedriver().setup(); 
//*From selenium 4.6.0 no need to write webdrivermanager
			
			d = new ChromeDriver(); 
			d.manage().window().maximize();

		} else if (browser.equalsIgnoreCase("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
			d = new FirefoxDriver();
			d.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("edge")) {
//			WebDriverManager.edgedriver().setup();
			d = new EdgeDriver();
			d.manage().window().maximize();
		}

		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		d.get(prop.getProperty("url"));

		return d;
	}

}
