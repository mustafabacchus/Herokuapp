import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FileUpload {
	public static WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/upload");
	}
		
	@Test(priority=1)
	public void fileUpload() {
		//Send path of file to upload
		driver.findElement(By.id("file-upload")).sendKeys("C:\\Users\\mustafa.bacchus\\Desktop\\testfile.txt");
		//Click the upload button
		driver.findElement(By.id("file-submit")).click();
		
		//Confirm file is uploaded
		assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).isDisplayed());
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	
}
