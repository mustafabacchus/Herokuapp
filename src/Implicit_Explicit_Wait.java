import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Implicit_Explicit_Wait {
	public static WebDriver driver;
	public static final int waitTime = 10;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	//EXPLICIT WAIT
	@Test(priority=1)
	public void hiddenElements() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
		
		//Declare wait
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		
		//Click the start button
		driver.findElement(By.xpath("//*[@id='start']/button")).click();
		//Ensure loading element is displayed
		assertTrue(driver.findElement(By.id("loading")).isDisplayed());
		
		//Explicit wait for hidden element
		WebElement postText = wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//*[@id=\"finish\"]/h4"))));
		
		//Ensure loaded text is displayed
		assertTrue(postText.isDisplayed());
		System.out.println("Post Rendered Text: " + postText.getText());
	}
	
	//IMPLICIT WAIT
	@Test(priority=1)
	public void postRender() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//Click the start button
		driver.findElement(By.xpath("//*[@id='start']/button")).click();
		//Ensure loading element is displayed
		assertTrue(driver.findElement(By.id("loading")).isDisplayed());

		//Implicit wait for render
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		//Locate and store post rendered element
		WebElement postText = driver.findElement(By.xpath("//*[@id=\"finish\"]/h4"));
		
		//Ensure loaded text is displayed
		assertTrue(postText.isDisplayed());
		System.out.println("Post Rendered Text: " + postText.getText());
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
