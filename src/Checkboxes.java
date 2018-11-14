import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Checkboxes {
	public static WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}
		
	@Test(priority=1)
	public void checkboxes() {
		driver.get("http://the-internet.herokuapp.com/checkboxes");	
		
		//Find elements
		WebElement check1 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
		WebElement check2 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]"));
		
		//Check boxes if not checked
		if (!check1.isSelected()) check1.click();
		if (!check2.isSelected()) check2.click();
		
		//Uncheck then check boxes
		int i = 0;
		while (i <= 2) {
			check1.click();
			check2.click();
			i++;
		}
		
		//Confirm unchecked
		assertTrue(!check1.isSelected());
		assertTrue(!check1.isSelected());

	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
