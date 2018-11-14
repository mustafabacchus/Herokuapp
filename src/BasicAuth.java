import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BasicAuth {
	public static WebDriver driver;
	
		@BeforeTest
		public void beforeTest() {
			System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
			
		@Test(priority=1)
		public void basicAuth() {
			driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");	
		}
		
		@AfterTest
		public void afterTest() {
			driver.quit();
		}
}
