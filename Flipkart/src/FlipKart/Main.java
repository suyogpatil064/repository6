/******************************************************************************************************************************************
 *  To demonstrate use of data driven concepts, code executes search for laptop, TV, washing machine apart from required search for mobiles
 *  *************************************************************************************************************************************** */
package FlipKart;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Main extends Base{

	@BeforeSuite
	public static void setProperty() throws IOException {
		prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\resources\\objectRepository\\propConfig.properties");
			prop.load(file);	
		dataArray=ReadFromExcel.readExcel( System.getProperty("user.dir") +
					"\\resources\\data", prop.getProperty("fileName"), prop.getProperty("sheetName"));
		driver=DriverSetup.getDriver(prop.getProperty("browserName"));
	}
	
	@BeforeTest
	public static void setDriver() throws IOException {
		
		
		reports = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\test-output\\testReport.html");
		htmlReporter.config().setDocumentTitle("E-Commerce Automation - Flipkart");
		reports.attachReporter(htmlReporter);
		homePage.closeLogin();
	}
	
	@Test (dataProvider="provider")
	public void checkTitle(String searchQuery, String maxPrice) throws InterruptedException {

		titleTest = reports.createTest("validate title for " + searchQuery);
		homePage.enterSearch(searchQuery);
		verifyTitle(searchQuery + "- Buy Products Online at Best Price in India - All Categories | Flipkart.com");
		
		priceTest = reports.createTest("validate price for " + searchQuery);
		priceTest.log(Status.INFO, "Search query for " + searchQuery + " entered");
		resultsPage.selectPrice(maxPrice);
		priceTest.log(Status.INFO, "Price of " + maxPrice + " selected");
		
		resultsPage.sortNewestFirst();
		priceTest.log(Status.INFO, "Sorted according to Newest first ");
		
		names = resultsPage.getProductNames();
		priceTest.log(Status.INFO, "Names of the "+ searchQuery +" extracted");
		prices = resultsPage.getProductPrices();
		priceTest.log(Status.INFO, "Prices of the "+ searchQuery +" extracted");
		resultsPage.validatePrice(maxPrice);
		resultsPage.displayResults();
		driver.navigate().to("http://flipkart.com");
	}
	
	@AfterTest
	public void closeBrowser() {
		
		driver.quit();
		reports.flush();
	}
	
	@DataProvider
	public String[][] provider() throws IOException{
//		String[][] data ={{"Mobiles", "30000"}, {"Laptops","60000"}};
//		return data;
		dataArray=ReadFromExcel.readExcel( System.getProperty("user.dir") +
				"\\resources\\data", prop.getProperty("fileName"), prop.getProperty("sheetName"));
		return dataArray;
	}
	
}
