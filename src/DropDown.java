import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DropDown {
	public static WebDriver driver;
	public static Select dropdown;
	public static String[] expectedOptions = {"Please select an option", "Option 1", "Option 2"};
	public static List<WebElement> actualOptions;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://the-internet.herokuapp.com/dropdown");
		
		dropdown = new Select (driver.findElement(By.id("dropdown")));
		actualOptions = dropdown.getOptions();
	}
	
	@Test(priority=1)
	public void dropdown() {
		int i = 0;
		for (WebElement option: actualOptions) {
			String actual = option.getText();
			String expected = expectedOptions[i];
			//Validate actual vs. expected
			assertEquals(actual, expected);
			
			//Validate option can be selected
			dropdown.selectByVisibleText(option.getText());
			
			i++;
		}
	}
	
	@Test(priority=1)
	public void notSelectableOption() {
		//Get the first option
		WebElement pleaseSelectOption = actualOptions.get(0);
		//Ensure first option matches expected
		assertEquals(pleaseSelectOption.getText(), expectedOptions[0]);
		//Get disabled attribute
		String valDisabled = pleaseSelectOption.getAttribute("disabled");
		//Ensure disabled
		assertEquals(valDisabled, "true");
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
