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
		//allMethods.extractFromDataArray(dataArray);
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
	
//	@Test (dataProvider="provider")
//	public void validatePrice(String searchQuery, String maxPrice) throws InterruptedException {
//		priceTest = reports.createTest("validate price" + searchQuery);
//		priceTest.log(Status.INFO, "Search query for " + searchQuery + " entered");
//		resultsPage.selectPrice(maxPrice);
//		priceTest.log(Status.INFO, "Price of " + maxPrice + " selected");
//		
//		resultsPage.sortNewestFirst();
//		priceTest.log(Status.INFO, "Sorted according to Newest first ");
//		
//		names = resultsPage.getProductNames();
//		priceTest.log(Status.INFO, "Names of the "+ searchQuery +"extracted");
//		prices = resultsPage.getProductPrices();
//		priceTest.log(Status.INFO, "Prices of the "+ searchQuery +" extracted");
//		resultsPage.validatePrice(maxPrice);
//		resultsPage.displayResults();
//	}

	@AfterTest
	public void closeBrowser() {
		
		driver.close();
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
	
	
//	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
//
//		String filePath = System.getProperty("user.dir") + "\\resources\\data"; // define excel sheet path
//		ArrayList<ArrayList<String>> dataArray = new ArrayList<ArrayList<String>>(); // ArrayList containing excel sheet
//		try {
//			dataArray = ReadFromExcel.readExcel(filePath, "searchProducts.xlsx", "sheet1"); // extract data from excel sheet
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("Chrome or Firefox or Opera or Edge?");
//		Scanner s = new Scanner(System.in);
//		String browser = s.nextLine();  // input from user for choosing browser
//		s.close();
//		driver = DriverSetup.getDriver(browser); // launch browser
//
//		allMethods.closeLogin(driver);	//close login request
//		
//		//loop through array list to get product and price from sheet and invoke all methods serially
//		for (int i = 1; i < dataArray.size(); i++) {
//			String maxPrice; // maximum price allowed
//			String searchQuery; // product to be searched
//			try {
//				maxPrice = dataArray.get(i).get(1);	
//				searchQuery = dataArray.get(i).get(0);
//			} catch (IndexOutOfBoundsException e) {
//				break; //to stop loop when all data finishes
//			}
//
//				Thread.sleep(8000);
//
//				allMethods.enterSearch(searchQuery, driver); // enter the search query in search box
//
//				allMethods.validateTitle(searchQuery, driver); // validate the title of the page
//
//				allMethods.closeLogin(driver);
//
//				allMethods.selectPrice(maxPrice, driver); // set max price
//
//				Thread.sleep(10000);
//
//				allMethods.sortNewestFirst(driver); // sort according to newest products
//
//				Thread.sleep(3000);
//
//			List<WebElement> names = allMethods.getProductNames(driver); // extract names of first 5 products
//			List<WebElement> prices = allMethods.getProductPrices(driver); // extract prices of first 5 products
//			
//			Thread.sleep(3000);
//			
//			allMethods.displayResults(names, prices, driver); // display names of first 5 products and price of first product
//
//			allMethods.validatePrice(prices, maxPrice, driver); // validate price of first product
//		}
//		driver.close(); // close the driver instance
//	}
	
}
