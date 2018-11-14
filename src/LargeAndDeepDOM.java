import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LargeAndDeepDOM {
	public static WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/large");			
	}
	
	@Test(priority=1)
	public void large() {
		//Get all the siblings
		List<WebElement> numbers = driver.findElements(By.xpath("//*[contains(@id, 'sibling-')]"));
		
		//Display the amount of siblings
		System.out.println("Total Siblings: " + numbers.size());
		
		//Display the first sibling with all children
		String allChildren = numbers.get(0).getText();
		System.out.println(allChildren);
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
