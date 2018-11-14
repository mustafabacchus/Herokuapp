import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DragAndDrop {
	public static WebDriver browser;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		browser = new ChromeDriver();
		browser.get("http://the-internet.herokuapp.com/drag_and_drop");

	}
	
	@Test(priority=1)
	public void dragAndDrop() {
		Actions cursor = new Actions(browser);
			
		WebElement a = browser.findElement(By.xpath("//*[@id=\"column-a\"]/header"));
		WebElement b = browser.findElement(By.xpath("//*[@id=\"column-b\"]/header"));
		cursor.dragAndDrop(a, b).build().perform();
		//cursor.clickAndHold(a).moveToElement(b).release(a).build().perform();
		
	}
	
	
	@AfterTest
	public void afterTest() {
		browser.quit();
	}
}
