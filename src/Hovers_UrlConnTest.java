import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Hovers_UrlConnTest {
	
	public static boolean validateLink(String href) throws IOException {
		boolean working = true;
		//Create url
		URL url = new URL(href);
		//Create a http connection
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
			connection.connect();
			//Get responses
			int code = connection.getResponseCode();
			String response = connection.getResponseMessage();
			//Display info
			System.out.println(href + ":");
			System.out.println(code + ": " + response);
			connection.disconnect();
		} catch (Exception e){
		//Fatal error
			working = false;
		}
		return working;
	}
	
	
	public static WebDriver driver;
	public List<WebElement> profiles = new ArrayList<>();
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/hovers");
		
		//Collect web elements
		profiles = driver.findElements(By.xpath("//*[@alt=\'User Avatar']"));
		//Display the numver of profiles on page
		System.out.println("Proflies displayed: " + profiles.size());
	}
	
	@Test(priority=1)
	public void hovers() {		
		Actions act = new Actions(driver);
		int n = 1;
		for (WebElement profile: profiles) {
			//Hover over profile
			act.moveToElement(profile).build().perform();
			
			//Construct path of profile info
			String userInfoPath = "//*[@id=\'content\']/div/div[" + n + "]/div/h5";
			//Ensure user info is displayed
			assertTrue(driver.findElement(By.xpath(userInfoPath)).isDisplayed());
			
			n++;
		}	
	}
	
	@Test(priority=1)
	public void validateProfileLinks() throws IOException {
		for (int n = 1; n < profiles.size()+1; n++) {
			//Make profile link path
			String viewProfileLinkPath = "//*[@id=\'content\']/div/div[" + n + "]/div/a";
			//Get the link
			String viewProfileLink = driver.findElement(By.xpath(viewProfileLinkPath)).getAttribute("href");
			//Validate link
			assertTrue(validateLink(viewProfileLink));
		}
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
}
