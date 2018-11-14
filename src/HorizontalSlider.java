import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HorizontalSlider {
	public static WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test(priority=1)
	public void slider() {
		driver.get("http://the-internet.herokuapp.com/horizontal_slider");
		
		//Find slider element
		WebElement slider = driver.findElement(By.xpath("//*[@type=\'range']"));
		
		//Declare action class
		Actions act = new Actions(driver);
		//Move slider
		act.moveToElement(slider).dragAndDropBy(slider, 10, 0).build().perform();
		
		//Display range moved on page
		String range = driver.findElement(By.id("range")).getText();
		System.out.println("Range Moved: " + range);
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	
}
