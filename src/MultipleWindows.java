import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MultipleWindows {
	public static WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/windows");
	}
	
	@Test(priority=1)
	public void newWindow() {
		//Open new tab multiple times
		int i = 0;
		while (i < 4) {
			//Find element
			driver.findElement(By.linkText("Click Here")).click(); 
			i++;
		}
		
		//Get all tabs and store as string
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		//Display total number of tabs
		System.out.println("Number of Tabs: " + tabs.size());
		
		for (int j = 0; j < tabs.size()-1; j++) {
			//Switch to tab
			driver.switchTo().window(tabs.get(j));
			//Assert correct title
			if (j == 0) {
				//Parent tab
				assertEquals(driver.getTitle(), "The Internet");
			} else {
				assertEquals(driver.getTitle(), "New Window");
			}
		}
		
		//Switch to the last open tab
		//driver.switchTo().window(tabs.get(tabs.size()-1));	
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
