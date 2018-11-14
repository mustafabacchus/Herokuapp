import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DisapperingElements {
	public static WebDriver driver;
	//Expected menu items
	String[] expectedItems = {"Home", "About", "Contact Us", "Portfolio"};//, "Gallery"};
	String[] actualItems;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/disappearing_elements");	
		
		//Get actual menu item elements
		List<WebElement> actualItemElements = driver.findElements(By.tagName("li"));
		//Define actual item array size
		actualItems = new String[actualItemElements.size()];
		int i = 0;
		for (WebElement menuItem: actualItemElements) {
			//Store the text of each menu item
			actualItems[i] = menuItem.getText();
			i++;
		}
	}
	
	@Test(priority=1)
	public void elementsVisible() {		
		boolean pass = true;
		for (String item: expectedItems) {
			try {
				//Item is present and displayed
				if (driver.findElement(By.linkText(item)).isDisplayed()) {
					System.out.println(item + ": DISPLAYED.");
				} else {
				//Item present and NOT displayed
					System.out.println(item + ": NOT DISPLAYED.");
					pass = false;
				}
			} catch (NoSuchElementException e) {
				//Item is NOT present
				System.out.println(item + ": NOT PRESENT.");
				pass = false;
			}
		}
		for (String item: actualItems) {
			//Check if other items than exepected are present
			if (!Arrays.asList(expectedItems).contains(item)) {
				System.out.println(item + ": NOT EXPECTED.");
				pass = false;
			}
		}
		
		System.out.println();
		//Pass if all items present, and only expected items exist
		assertTrue(pass);
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
