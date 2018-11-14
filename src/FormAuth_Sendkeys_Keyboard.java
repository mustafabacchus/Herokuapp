import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FormAuth_Sendkeys_Keyboard {
	public static WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();		
	}
	
	@Test(priority=1)
	public void validLoginSendKeys() {
		driver.get("http://the-internet.herokuapp.com/login");	
		
		 Map<String,String> cred = new HashMap<String, String>()
			{{
				put("user", "tomsmith");
				put("pass", "SuperSecretPassword!");
			}};
		
			
		//Enter username
		driver.findElement(By.id("username")).sendKeys(cred.get("user"));
		//Enter password
		driver.findElement(By.id("password")).sendKeys(cred.get("pass"));
		//Sign in
		driver.findElement(By.xpath("//*[@class=\"fa fa-2x fa-sign-in\"]")).click();
		
		//Ensure user is signed in
		WebElement flash = driver.findElement(By.id("flash"));
		assertTrue(flash.isDisplayed());
		assertTrue(flash.getText().toLowerCase().contains("you logged into a secure area!"));
		//Logout
		driver.findElement(By.xpath("//*[@class=\"icon-2x icon-signout\"]")).click();
	}
	
	@Test(priority=1)
	public void validLoginKeyboard() {
		driver.get("http://the-internet.herokuapp.com/login");	
		
		 Map<String,String> cred = new HashMap<String, String>()
			{{
				put("user", "tomsmith");
				put("pass", "SuperSecretPassword!");
			}};
			
			
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		//Enter username
		act.sendKeys(Keys.TAB).sendKeys(cred.get("user")).build().perform();
		//Enter password
		act.sendKeys(Keys.TAB).sendKeys(cred.get("pass")).build().perform();
		//Sign in
		act.sendKeys(Keys.ENTER).build().perform();
		
		//Ensure user is signed in
		WebElement flash = driver.findElement(By.id("flash"));
		assertTrue(flash.isDisplayed());
		assertTrue(flash.getText().toLowerCase().contains("you logged into a secure area!"));
		//Logout
		act.sendKeys(Keys.TAB).build().perform();
		act.sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
	}
	
	@Test(priority=1)
	public void invaildLoginSendKeys() {
		driver.get("http://the-internet.herokuapp.com/login");	
		
		Map<String, String> cred = new HashMap<String, String>() 
			{{
				put("user", "kristina");
				put("pass", "@dmfne$53v");
			}};

			
		//Enter username
		driver.findElement(By.id("username")).sendKeys(cred.get("user"));
		//Enter password
		driver.findElement(By.id("password")).sendKeys(cred.get("pass"));
		//Sign in
		driver.findElement(By.xpath("//*[@class=\"fa fa-2x fa-sign-in\"]")).click();
		
		//Ensure user is NOT signed in
		WebElement flash = driver.findElement(By.id("flash"));
		assertTrue(flash.isDisplayed());
		assertTrue(flash.getText().toLowerCase().contains("your username is invalid!"));
		//User not logged in when login button is present
		assertTrue(driver.findElement(By.xpath("//*[@class=\"fa fa-2x fa-sign-in\"]")).isDisplayed());
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
}
