import static org.testng.Assert.assertNotEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChallengingDOM {
	public static WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/challenging_dom");			
	}
		
	@Test(priority=1)
	public void dynamicButtons() {
		String[] buttonPath = {"//*[@class='button']", "//*[@class='button alert']", "//*[@class='button success']"};
		
		for (String path: buttonPath) {
			//Get the orginal id
			String before = driver.findElement(By.xpath(path)).getAttribute("id");
			//Click the button
			driver.findElement(By.xpath(path)).click();
			//Get the post id
			String after = driver.findElement(By.xpath(path)).getAttribute("id");
			//Check that ids have changed
			assertNotEquals(before, after);
		}
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
