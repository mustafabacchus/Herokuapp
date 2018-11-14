import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JsAlerts {
	public static WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//Open webpage
		driver.get("http://the-internet.herokuapp.com/javascript_alerts");
	}
	
	@Test(priority=1)
	public void jsAlert() {
		//Click JS alert
		driver.findElement(By.xpath("//*[contains(text(), 'Click for JS Alert')]")).click();
		driver.switchTo().alert().accept();
		
		String result = driver.findElement(By.id("result")).getText();
		assertEquals(result, "You successfuly clicked an alert");
	}
	
	@Test(priority=1)
	public void jsConfirm() {
		//Click JS alert and confirm
		driver.findElement(By.xpath("//*[contains(text(), 'Click for JS Confirm')]")).click();
		driver.switchTo().alert().accept();
		
		String result = driver.findElement(By.id("result")).getText();
		assertEquals(result, "You clicked: Ok");
		
		
		//Click JS alert and cancel
		driver.findElement(By.xpath("//*[contains(text(), 'Click for JS Confirm')]")).click();
		driver.switchTo().alert().dismiss();
		
		result = driver.findElement(By.id("result")).getText();
		assertEquals(result, "You clicked: Cancel");
	}
	
	@Test(priority=1)
	public void jsPrompt() {
		String text[] = {"Hello world!", "bean bag", "?", "mooon", "kool"};
		String result = "";
		
		//Enter text and accept
		for (String prompt: text) {
			driver.findElement(By.xpath("//*[contains(text(), 'Click for JS Prompt')]")).click();
			driver.switchTo().alert().sendKeys(prompt);
			driver.switchTo().alert().accept();
			result = driver.findElement(By.id("result")).getText();	
			assertEquals(result, "You entered: " + prompt);
		}
		
		//Enter text and cancel
		for (String prompt: text) {
			driver.findElement(By.xpath("//*[contains(text(), 'Click for JS Prompt')]")).click();
			driver.switchTo().alert().sendKeys(prompt);
			driver.switchTo().alert().dismiss();
			result = driver.findElement(By.id("result")).getText();	
			assertEquals(result, "You entered: null");
		}
			
		//Enter NO text and accept
		driver.findElement(By.xpath("//*[contains(text(), 'Click for JS Prompt')]")).click();	
		driver.switchTo().alert().accept();
		result = driver.findElement(By.id("result")).getText();	
		assertEquals(result, "You entered:");
		
		//Enter NO text and cancel
		driver.findElement(By.xpath("//*[contains(text(), 'Click for JS Prompt')]")).click();	
		driver.switchTo().alert().dismiss();
		result = driver.findElement(By.id("result")).getText();	
		assertEquals(result, "You entered: null");
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
