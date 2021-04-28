package utils;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class seleniumUtils {
	
public static WebDriver driver;
	
	/* Click methods */
	public static void clickById(String id) {
		driver.findElement(By.id(id)).click();
	}
	public static void clickByXpath(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	public static void clickByClass(String className) {
		driver.findElement(By.className(className)).click();
	}
	public static void clickByText(String linkText) {
		driver.findElement(By.linkText(linkText)).click();
	}
	
	/* Text-box methods */
	public static void textById(String id, String text) {
		driver.findElement(By.id(id)).sendKeys(text);
	}
	public static void textByXpath(String xpath, String text) {
		driver.findElement(By.xpath(xpath)).sendKeys(text);
	}
	public static void textByClass(String className, String text) {
		driver.findElement(By.className(className)).sendKeys(text);
	}
	
	/* Select methods for checkbox and radio button */
	public static void selectById(String id) {
		driver.findElement(By.id(id)).click();
	}
	public static void selectByXpath(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	public static void selectByClass(String className) {
		driver.findElement(By.className(className)).click();
	}
	
	/* Select methods drop-down */
	public static Select selectById(String id, String visibleText) {
		Select selectObj = new Select(driver.findElement(By.id(id)));
		selectObj.selectByVisibleText(visibleText);
		return selectObj;
	}
	public static Select selectByXpath(String xpath, String visibleText) {
		Select selectObj = new Select(driver.findElement(By.xpath(xpath)));
		selectObj.selectByVisibleText(visibleText);
		return selectObj;
	}
	public static Select selectByClass(String className, String visibleText) {
		Select selectObj = new Select(driver.findElement(By.className(className)));
		selectObj.selectByVisibleText(visibleText);
		return selectObj;
	}
	
	/* driver navigation methods */
	public static void back() {
		driver.navigate().back();
	}
	public static void refresh() {
		driver.navigate().refresh();
	}
	public static void goTo(String url) {
		driver.navigate().to(url);
	}
	public static void forward() {
		driver.navigate().forward();
	}
	
	/* setup driver*/
	public static WebDriver getDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
//			System.setProperty("webdriver.chrome.driver",
//					System.getProperty("user.dir") + "\\resources\\drivers\\chromedriver.exe");
			WebDriverManager.chromedriver().version("90.0.4430.24").setup();
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.gecko.driver",
//					System.getProperty("user.dir") + "\\resources\\drivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			//ProfilesIni settings = new ProfilesIni();
			//FirefoxProfile profile = settings.getProfile("selenium");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setProfile(new FirefoxProfile());
			driver = new FirefoxDriver(firefoxOptions);

			// driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
//			System.setProperty("webdriver.edge.driver",
//					System.getProperty("user.dir") + "\\resources\\drivers\\msedgedriver.exe");
					WebDriverManager.edgedriver().setup();
					ChromeOptions chromeOptions = new ChromeOptions();
			        chromeOptions.setBinary(
			                "C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
			        EdgeOptions edgeOptions = new EdgeOptions().merge(chromeOptions);
			        driver = new EdgeDriver(edgeOptions);
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("opera")) {
//			System.setProperty("webdriver.opera.driver",
//					System.getProperty("user.dir") + "\\resources\\drivers\\operadriver.exe");
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS); // page load time out
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS); //No such element found

		String baseUrl = "https://www.flipkart.com";
		driver.get(baseUrl);
		return driver;
	}
	
}
