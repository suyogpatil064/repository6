package FlipKart;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class resultsPage extends Base{
	
	private static By priceElement = By.className("_3uDYxP");
	private static By maxPriceElement = By.className("_2YxCDZ");
	private static By sortElement =  By.className("_10UF8M");
	private static By allProductsNames = By.className("_4rR01T");
	private static By allProductsPrices = By.cssSelector("div[class='_30jeq3 _1_WHN1']");
	private static By searchBar = (By.name("q"));
	
	public static void enterSearch(String searchQuery) throws InterruptedException {
		
		driver.findElement(searchBar).clear();
//		Thread.sleep(5000);
		driver.findElement(searchBar).sendKeys(Keys.CONTROL + "a");
		driver.findElement(searchBar).sendKeys(Keys.DELETE);
		driver.findElement(searchBar).sendKeys(searchQuery + Keys.ENTER);
		titleTest.log(Status.INFO, "Search query for " + searchQuery + " entered");
	}
	
	// set max price
			public static void selectPrice(String maxPrice) throws InterruptedException {
				WebElement price=null;

				price = driver.findElement(priceElement); // elements which have price drop downs
				Select priceDropDown = new Select(price.findElement(maxPriceElement)); // drop down element which selects higher price
				priceDropDown.selectByValue(maxPrice);
			}

			// sort according to 'newest first'
			public static void sortNewestFirst() throws InterruptedException {
				
				Thread.sleep(5000);
				
				List<WebElement> sortOpt = driver.findElements(sortElement); // List of elements of all sorting options
				// loop through the list to find 'newest first' option
				for (int i = 0; i < sortOpt.size(); i++) {
					if (sortOpt.get(i).getText().equals("Newest First")) {
						sortOpt.get(i).click();
						
					}
				}
			}

			// get names of all products
			public static List<WebElement> getProductNames() {
				return driver.findElements(allProductsNames);
			}

			// get List of all prices
			public static List<WebElement> getProductPrices() {
				return driver.findElements(allProductsPrices);
			}

			// validate whether price of first product is below max price
			public static void validatePrice(String maxPrice) {
				// remove all symbols from price string to compare it with max price
				String firstPriceS = prices.get(0).getText();
				firstPriceS = firstPriceS.substring(1); // remove rupees symbol
				for (int i = 0; i < firstPriceS.length(); i++) {
					if (firstPriceS.charAt(i) == ',') {
						firstPriceS = firstPriceS.substring(0, i) + firstPriceS.substring(i + 1); // remove comma (,)
					}
				}
				int firstPriceI = Integer.parseInt(firstPriceS);
				// check whether price is below max price
				if (firstPriceI > Integer.parseInt(maxPrice)) {
					System.out.println("Price of first product is greater than " + maxPrice);
					priceTest.log(Status.FAIL,"Validate Price test failed");
				} else {
					System.out.println("Price correctly below " + maxPrice);
					priceTest.log(Status.PASS,"Validate Price test passed");
				}
			}

			// display names of first 5 products and price of first product
			public static void displayResults() {
				System.out.println("Names of first 5 products are:");
					for (int i = 1; i < 6; i++) {
					System.out.println(i + ". " + names.get(i - 1).getText());
					System.out.println("Price of first product is: ");
					System.out.println(prices.get(0).getText().substring(1));
				
			}
}
}
