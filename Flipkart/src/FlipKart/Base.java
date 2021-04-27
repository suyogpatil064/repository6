package FlipKart;

import java.util.Properties;
import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
public class Base {

	static WebDriver driver;
	public static Properties prop;
	static String[][] dataArray; // ArrayList containing excel sheet
//	static String maxPrice;
//	static String searchQuery;
	static List<WebElement> names; // extract names of first 5 products
	static List<WebElement> prices; // extract prices of first 5 products
	static ExtentReports reports;
	static ExtentTest titleTest;
	static ExtentTest priceTest;
	static ExtentHtmlReporter htmlReporter;
	
	
	public static void verifyTitle(String expectedTitle) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait (driver,15);
		wait.until(ExpectedConditions.titleContains(expectedTitle));
		
		String actualTitle = driver.getTitle();
		if(actualTitle.equalsIgnoreCase(expectedTitle)) {
			titleTest.log(Status.PASS,"Validate Title test passed");
			titleTest.log(Status.INFO,"Title is " + actualTitle);
		}else {
			titleTest.log(Status.FAIL,"Validate Title test failed");
			titleTest.log(Status.INFO,"Title is " + actualTitle);
		}
	}
	
}